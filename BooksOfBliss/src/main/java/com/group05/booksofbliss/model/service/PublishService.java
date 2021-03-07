package com.group05.booksofbliss.model.service;

import com.group05.booksofbliss.model.dao.AuthorDAO;
import com.group05.booksofbliss.model.dao.BookDAO;
import com.group05.booksofbliss.model.dao.CategoryDAO;
import com.group05.booksofbliss.model.dao.ListingDAO;
import com.group05.booksofbliss.model.entity.Author;
import com.group05.booksofbliss.model.entity.Book;
import com.group05.booksofbliss.model.entity.Category;
import com.group05.booksofbliss.model.entity.Listing;
import java.io.Serializable;
import java.util.ArrayList;
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
    
    private void insertAuthor(Author author) {
        if (authorDAO.find(author.getName()) == null){
            authorDAO.create(author);
        }
    }
    private void insertBook(Book book) {
        if (bookDAO.find(book.getIsbn()) == null){
            bookDAO.create(book);  
        }
        
    }
    private void insertCategory(Category category) {
        if (categoryDAO.find(category.getName()) == null){
            categoryDAO.create(category);
        }
    }
    public void publishListing(Listing listing) {
        Book book = listing.getBook();
        List<Author> authors = book.getAuthors();
        List<Category> categories = book.getCategories();
        //Check that everything required for a listing exists in the database,
        //and inserts it if it doesn't exist.
        authors.forEach(author -> {
            insertAuthor(author);
        });
        categories.forEach(category -> {
            insertCategory(category);
        });
        insertBook(book);
        
        //insertBook(isbn, title);
        //listingDAO.create(new Listing());
    }
}
