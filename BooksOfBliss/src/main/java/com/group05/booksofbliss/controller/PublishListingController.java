package com.group05.booksofbliss.controller;

import com.group05.booksofbliss.model.dao.ConditionDAO;
import com.group05.booksofbliss.model.entity.Account;
import com.group05.booksofbliss.model.entity.Author;
import com.group05.booksofbliss.model.entity.Book;
import com.group05.booksofbliss.model.entity.Category;
import com.group05.booksofbliss.model.entity.Condition;
import com.group05.booksofbliss.model.entity.Listing;
import com.group05.booksofbliss.model.service.PublishService;
import com.group05.booksofbliss.security.Auth;
import com.group05.booksofbliss.service.IsbnApi;
import com.group05.booksofbliss.view.PublishListingBackingBean;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.money.MonetaryAmount;
import lombok.Data;
import org.javamoney.moneta.Money;

@Data
@RequestScoped
@Named
public class PublishListingController implements Serializable {

    @Inject
    private PublishListingBackingBean publishListingBackingBean;
    
    @Inject
    private PublishService publishService;
    
    @Inject
    private Auth auth;
    
    public void searchIsbn() throws IOException, InterruptedException {
        String isbn = publishListingBackingBean.getIsbn();
        System.out.println("ISBN: " + isbn);
        publishListingBackingBean.setTitle(IsbnApi.getTitle(isbn));
        publishListingBackingBean.setAuthors(IsbnApi.getAuthors(isbn));
        publishListingBackingBean.setImageUrl(IsbnApi.getImageUrl(isbn));
        publishListingBackingBean.setCategories(IsbnApi.getBookCategories(isbn));
        publishListingBackingBean.setPublishDate(IsbnApi.getPublishDate(isbn));
    }
    public void publish() {
        //Variables for creating listing and book objects
        String isbn = publishListingBackingBean.getIsbn();
        String title = publishListingBackingBean.getTitle();
        String imageUrl = publishListingBackingBean.getImageUrl();
        int publishYear = publishListingBackingBean.getPublishDate();
        List<Author> authors = new ArrayList();
        List<Category> categories = new ArrayList();
        publishListingBackingBean.getAuthors().forEach(author -> {
            authors.add(new Author(author));
        });
        publishListingBackingBean.getCategories().forEach(category -> {
            categories.add(new Category(category));
        });
        
        //Create book object
        Book book = new Book(isbn, title, publishYear, imageUrl);
        book.setAuthors(authors);
        book.setCategories(categories);
        
        //Create listing object
        Date date = new Date();
        BigDecimal price = publishListingBackingBean.getPrice();
        String description = publishListingBackingBean.getDescription();
        //String conditionName = publishListingBackingBean.getConditionName();
        Condition condition = new Condition(publishListingBackingBean.getConditionName());
        Account acc = auth.getAccount();
        System.out.println("inloggad användare: " + auth.getAccount());
        Listing listing = new Listing(date, Money.of(price, "SEK"), description, condition, acc, book);
        System.out.println("Innan publishListing");
        publishService.publishListing(listing);
    }
}
