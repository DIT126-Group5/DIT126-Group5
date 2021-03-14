package com.group05.booksofbliss.model.service;

import com.group05.booksofbliss.Deployments;
import com.group05.booksofbliss.model.dao.AccountDAO;
import com.group05.booksofbliss.model.dao.AuthorDAO;
import com.group05.booksofbliss.model.dao.BookDAO;
import com.group05.booksofbliss.model.dao.CategoryDAO;
import com.group05.booksofbliss.model.dao.ConditionDAO;
import com.group05.booksofbliss.model.dao.ListingDAO;
import com.group05.booksofbliss.model.entity.Account;
import com.group05.booksofbliss.model.entity.Author;
import com.group05.booksofbliss.model.entity.Book;
import com.group05.booksofbliss.model.entity.Category;
import com.group05.booksofbliss.model.entity.Condition;
import com.group05.booksofbliss.model.entity.Listing;
import com.group05.booksofbliss.model.entity.attribute.Address;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.javamoney.moneta.Money;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class PublishServiceTest {
    
    @Deployment
    public static WebArchive createDeployment() {
        return Deployments.defaultArchive();
    }
    
    @Inject
    private PublishService publishService;
    
    @Inject
    private AccountDAO accountDAO;
    
    @Inject
    private ListingDAO listingDAO;
    
    @Inject
    private BookDAO bookDAO;
    
    @Inject
    private AuthorDAO authorDAO;
    
    @Inject
    private ConditionDAO conditionDAO;
    
    @Inject
    private CategoryDAO categoryDAO;
            
    
    Account acc;
    Author author;
    Author author2;
    Author author3;
    Book book;
    Book book2;
    Condition condition;
    Listing listing;
    Listing listing2;
    Category category;
    Category category2;
    Category category3;
    
    
    
    @Before
    public void init() {
        acc = new Account("username", "firstname", "lastname", "0777777777", "namn@mail.se", "password", new Address("Street", "45163", "City"), Money.of(50, "SEK"));
        accountDAO.create(acc);
        
        author = new Author("Henning Mankell");
        author2 = new Author("Jonatan Swift");
        author3 = new Author("Jane Austen");
        
        //Author3 should already be created when calling publishService.publishListing()
        authorDAO.create(author3);
        
        List<Author> authors = new ArrayList();
        List<Author> authors2 = new ArrayList();
        authors.add(author);
        authors.add(author2);
        authors2.add(author3);
        
        category = new Category("Roman");
        category2 = new Category("Datorer");
        category3 = new Category("Matematik");
        
        //Category3 should already be created when calling publishService.publishListing()
        categoryDAO.create(category3);
        
        List<Category> categories = new ArrayList();
        List<Category> categories2 = new ArrayList();
        categories.add(category);
        categories.add(category2);
        categories2.add(category3);
        
        book = new Book("9789144151458", "Hållbar utveckling", 2021, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.adlibris.com%2Fse%2Fbok%2Fhallbar-utveckling---livskvalitet-beteende-och-teknik-9789144151458&psig=AOvVaw0m1oxt_x4E6gVfR-KKcUMB&ust=1614764579235000&source=images&cd=vfe&ved=0CA0QjhxqFwoTCJjEkvuoke8CFQAAAAAdAAAAABAJ");
        book.setAuthors(authors);
        book.setCategories(categories);
        
        book2 = new Book("1234567890", "En bok", 2019, "");
        book2.setAuthors(authors2);
        book2.setCategories(categories2);

        //Book2 should already be created when calling publishService.publishListing()
        bookDAO.create(book2);
        
        condition = new Condition("Toppenskick");
        conditionDAO.create(condition);
        
        listing = new Listing(new Date(1615125000000L), Money.of(50, "SEK"), "description", condition, acc, book);
        listing2 = new Listing(new Date(1615126000000L), Money.of(50, "SEK"), "description", condition, acc, book2);     
        
    }
    @After
    public void clean() {
        listingDAO.remove(listing);
        listingDAO.remove(listing2);
        bookDAO.remove(book);
        bookDAO.remove(book2);
        categoryDAO.remove(category);
        categoryDAO.remove(category2);
        categoryDAO.remove(category3);
        authorDAO.remove(author);
        authorDAO.remove(author2);
        authorDAO.remove(author3);
        conditionDAO.remove(condition);
        accountDAO.remove(acc);
    }
    
    @Test
    public void publishServiceCreatesAuthors() {
        publishService.publishListing(listing);
        Assert.assertNotNull(authorDAO.find(author.getName()));
        Assert.assertNotNull(authorDAO.find(author2.getName()));
    }
    @Test
    public void publishServiceCreatesCategories() {
        publishService.publishListing(listing);
        Assert.assertNotNull(categoryDAO.find(category.getName()));
        Assert.assertNotNull(categoryDAO.find(category2.getName()));
    }
    @Test
    public void publishServiceCreatesBook() {
        publishService.publishListing(listing);
        Assert.assertNotNull(bookDAO.find(book.getIsbn()));
    }
    @Test
    public void publishServiceCreatesListing() {
        publishService.publishListing(listing);
        Assert.assertNotNull(listingDAO.find(listing.getId()));
    }
    @Test
    public void alreadyCreatedAuthorStillExist() {
        publishService.publishListing(listing2);
        Assert.assertNotNull(authorDAO.find(author3.getName()));
    }
    @Test
    public void alreadyCreatedCategoryStillExist() {
        publishService.publishListing(listing2);
        Assert.assertNotNull(categoryDAO.find(category3.getName()));
    }
    @Test
    public void alreadyCreatedBookStillExist() {
        publishService.publishListing(listing2);
        Assert.assertNotNull(bookDAO.find(book2.getIsbn()));
    }
}
