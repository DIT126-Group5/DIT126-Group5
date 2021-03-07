package com.group05.booksofbliss.controller;

import com.group05.booksofbliss.model.entity.Author;
import com.group05.booksofbliss.model.entity.Book;
import com.group05.booksofbliss.model.entity.Category;
import com.group05.booksofbliss.model.entity.Listing;
import com.group05.booksofbliss.model.service.PublishService;
import com.group05.booksofbliss.service.IsbnApi;
import com.group05.booksofbliss.view.PublishListingBackingBean;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.money.MonetaryAmount;
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
        publishListingBackingBean.setTitle(IsbnApi.getTitle(isbn));
        publishListingBackingBean.setAuthors(IsbnApi.getAuthors(isbn));
        publishListingBackingBean.setImageUrl(IsbnApi.getImageUrl(isbn));
        publishListingBackingBean.setCategories(IsbnApi.getBookCategories(isbn));
        publishListingBackingBean.setPublishDate(IsbnApi.getPublishDate(isbn));
    }
    public void publish() {
        String isbn = publishListingBackingBean.getIsbn();
        String title = publishListingBackingBean.getTitle();
        List<Author> authors = new ArrayList();
        List<Category> categories = new ArrayList();
        for (String author: publishListingBackingBean.getAuthors()){
            authors.add(new Author(author));
        }
        for (String category: publishListingBackingBean.getCategories()){
            categories.add(new Category(category));
        }
        String imageUrl = publishListingBackingBean.getImageUrl();
        int publishYear = publishListingBackingBean.getPublishDate();
        //Book book = new Book(isbn, title, publishYear, imageUrl, authors, categories);
        
        Listing listing = new Listing();
        publishService.publishListing(listing);
    }
}
