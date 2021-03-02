package com.group05.booksofbliss.model.service;

import com.group05.booksofbliss.model.dao.AuthorDAO;
import com.group05.booksofbliss.model.dao.BookDAO;
import com.group05.booksofbliss.model.dao.CategoryDAO;
import com.group05.booksofbliss.model.dao.ListingDAO;
import com.group05.booksofbliss.model.entity.Author;
import com.group05.booksofbliss.model.entity.Book;
import com.group05.booksofbliss.model.entity.Category;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PublishService implements Serializable {
    
    @Inject
    private ListingDAO listingDAO;
    
    @Inject
    private AuthorDAO authorDAO;
    
    @Inject
    private BookDAO bookDAO;
    
    @Inject
    private CategoryDAO categoryDAO;
    
    private void insertAuthor(String author) {
        authorDAO.create(new Author(author));
    }
    private void insertBook(String title) {
        bookDAO.create(new Book());
    }
    private void insertCategory(String category) {
        categoryDAO.create(new Category(category));
    }
    public void publishListing(String title, List<String> authors, List<String> categories) {
        //Insert everything required for a listing in the right order.
        for (String author : authors){
            insertAuthor(author);
        }
        for (String category : categories){
            insertCategory(category);
        }
        insertBook(title);
    }
}
