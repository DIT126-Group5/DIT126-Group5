package com.group05.booksofbliss.controller;

import com.group05.booksofbliss.model.dao.AuthorDAO;
import com.group05.booksofbliss.model.dao.BookDAO;
import com.group05.booksofbliss.model.dao.CategoryDAO;
import com.group05.booksofbliss.model.dao.ListingDAO;
import com.group05.booksofbliss.model.service.PublishService;
import com.group05.booksofbliss.service.IsbnApi;
import com.group05.booksofbliss.view.PublishListingBackingBean;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;

@Data
@RequestScoped
@Named
public class PublishListingController implements Serializable {

    @Inject
    private PublishListingBackingBean publishListingBackingBean;
    
    @Inject
    private PublishService publishService;
    
    public void searchIsbn() throws IOException, InterruptedException {
        String isbn = publishListingBackingBean.getIsbn();
        publishListingBackingBean.setTitle(IsbnApi.getTitleFromApi(isbn));
        publishListingBackingBean.setAuthors(IsbnApi.getAuthorsFromApi(isbn));
        publishListingBackingBean.setImageUrl(IsbnApi.getImageUrl(isbn));
        publishListingBackingBean.setCategories(IsbnApi.getBookCategories(isbn));
    }
    public void publish() {
        String title = publishListingBackingBean.getTitle();
        List<String> authors = publishListingBackingBean.getAuthors();
        List<String> categories = publishListingBackingBean.getCategories();
        publishService.publishListing(title, authors, categories);
    }
}
