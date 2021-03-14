package com.group05.booksofbliss.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONException;
import org.primefaces.shaded.json.JSONObject;

@ApplicationScoped
public class BookLookupService {

    @Getter
    @AllArgsConstructor
    @EqualsAndHashCode
    public static final class LookupResult {

        private final String title;
        private final List<String> authors;
        private final String imageUrl;
        private final List<String> categories;
        private final int publishYear;

        public boolean isValid() {
            return getTitle() != null
                    && getAuthors() != null
                    && getPublishYear() != -1;
        }
    }

    public LookupResult lookupByIsbn(String isbn) throws IOException, InterruptedException {
        String isbnUrl = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(isbnUrl))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject jo = new JSONObject(response.body());
            if (jo.getInt("totalItems") == 0) {
                return null;
            }

            return new LookupResult(
                    getTitle(jo),
                    getAuthors(jo),
                    getImageUrl(jo),
                    getBookCategories(jo),
                    getPublishDate(jo)
            );

        } catch (URISyntaxException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private String getTitle(JSONObject jo) {
        try {
            String title = jo.getJSONArray("items")
                    .getJSONObject(0)
                    .getJSONObject("volumeInfo")
                    .getString("title");
            return title;
        } catch (JSONException e) {
            return null;
        }

    }

    private List<String> getAuthors(JSONObject jo) {
        try {
            JSONArray jsonAuthors = jo.getJSONArray("items")
                    .getJSONObject(0)
                    .getJSONObject("volumeInfo")
                    .getJSONArray("authors");
            List<String> authors = new ArrayList();

            for (Object author : jsonAuthors) {
                authors.add(author.toString());
            }
            return authors;
        } catch (JSONException e) {
            return null;
        }
    }

    private String getImageUrl(JSONObject jo) {
        try {
            String imgLink = jo.getJSONArray("items")
                    .getJSONObject(0)
                    .getJSONObject("volumeInfo")
                    .getJSONObject("imageLinks")
                    .getString("thumbnail");
            return imgLink;
        } catch (JSONException e) {
            return null;
        }
    }

    private List<String> getBookCategories(JSONObject jo) {
        try {
            JSONArray jsonCategories = jo.getJSONArray("items")
                    .getJSONObject(0)
                    .getJSONObject("volumeInfo")
                    .getJSONArray("categories");

            List<String> categories = new ArrayList();
            for (Object category : jsonCategories) {
                categories.add(category.toString());
            }

            return categories;

        } catch (JSONException e) {
            return null;
        }

    }

    private int getPublishDate(JSONObject jo) {
        try {
            String publishYear = jo.getJSONArray("items")
                    .getJSONObject(0)
                    .getJSONObject("volumeInfo")
                    .getString("publishedDate");

            publishYear = publishYear.substring(0, 4);
            return Integer.parseInt(publishYear);
        } catch (JSONException e) {
            return -1;
        }
    }
}
