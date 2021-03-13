package com.group05.booksofbliss.controller;

import com.group05.booksofbliss.model.entity.Account;
import com.group05.booksofbliss.model.entity.Author;
import com.group05.booksofbliss.model.entity.Book;
import com.group05.booksofbliss.model.entity.Category;
import com.group05.booksofbliss.model.entity.Condition;
import com.group05.booksofbliss.model.entity.Listing;
import com.group05.booksofbliss.model.service.PublishService;
import com.group05.booksofbliss.security.Auth;
import com.group05.booksofbliss.service.BookLookupService;
import com.group05.booksofbliss.view.PublishListingBackingBean;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.javamoney.moneta.Money;

@RequestScoped
@Named
public class PublishListingController implements Serializable {

    @Inject
    private PublishListingBackingBean publishListingBackingBean;

    @Inject
    private PublishService publishService;

    @Inject
    private Auth auth;

    @Inject
    private BookLookupService bookLookupService;

    @Inject
    private FacesContext facesContext;

    @Inject
    private ExternalContext externalContext;

    public void searchIsbn() throws IOException, InterruptedException {
        //Title, author(s), publishdate are required from ISBN. Other fields allows null-values.
        String isbn = publishListingBackingBean.getIsbn();
        BookLookupService.LookupResult result = bookLookupService.lookupByIsbn(isbn);
        if (result == null || !result.isValid()) {
            facesContext.addMessage(null, new FacesMessage(SEVERITY_ERROR, "Vi hittade ingen bok som matchade det angivna ISBN-numret.", null));
            publishListingBackingBean.setShowPublishForm(false);
        } else {
            publishListingBackingBean.setTitle(result.getTitle());
            publishListingBackingBean.setAuthors(result.getAuthors());
            publishListingBackingBean.setImageUrl(result.getImageUrl());
            publishListingBackingBean.setCategories(result.getCategories());
            publishListingBackingBean.setPublishDate(result.getPublishYear());
            publishListingBackingBean.setShowPublishForm(true);
        }
    }

    public void publish() throws IOException {
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
        Condition condition = new Condition(publishListingBackingBean.getConditionName());
        Account acc = auth.getAccount();
        Listing listing = new Listing(date, Money.of(price, "SEK"), description, condition, acc, book);
        publishService.publishListing(listing);
        //Redirects user to the published listing.
        externalContext.redirect(externalContext.getRequestContextPath() + "/listing/" + listing.getId());

    }
}
