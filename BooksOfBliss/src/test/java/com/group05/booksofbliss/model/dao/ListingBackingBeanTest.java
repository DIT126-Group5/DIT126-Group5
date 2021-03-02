package com.group05.booksofbliss.model.dao;


import com.group05.booksofbliss.model.dao.AccountDAO;
import com.group05.booksofbliss.model.entity.Account;
import com.group05.booksofbliss.model.entity.Author;
import com.group05.booksofbliss.model.entity.Book;
import com.group05.booksofbliss.model.entity.Category;
import com.group05.booksofbliss.model.entity.Condition;
import com.group05.booksofbliss.model.entity.Listing;
import com.group05.booksofbliss.model.entity.attribute.Address;
import com.group05.booksofbliss.view.ListingBackingBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.javamoney.moneta.Money;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ListingBackingBeanTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "com.group05.booksofbliss")
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    private ListingBackingBean listisngBB;
    
    @Inject
    private ListingDAO listingDAO;
    
    @Inject
    private CategoryDAO categoryDAO;

    @Inject
    private ConditionDAO conditionDAO;
    
    @Inject
    private AuthorDAO authorDAO;

    @Inject
    private BookDAO bookDAO;
    
    @Inject
    private AccountDAO accountDAO;
    
    Account acc;
    Category category;
    List<Category> categories;
    Author author;
    Author author2;
    List<Author> authors;
    Book book;
    Condition condition;
    Listing listing;
    
    @Before
    public void init() {
        acc = new Account("username", "firstname", "lastname", "0777777777", "namn@mail.se", "password", new Address("Street", "45163", "City"), Money.of(50, "SEK"));
        accountDAO.create(acc);
        
        category = new Category("Hållbarhet");
        categoryDAO.create(category);
        categories = new ArrayList<>();
        categories.add(category);
        
        author = new Author("Håkan Gulliksson");
        author2 = new Author("Ulf Holmgren");
        authorDAO.create(author);
        authorDAO.create(author2);
        
        authors = new ArrayList<Author>();
        authors.add(author);
        authors.add(author2);
        book = new Book("9789144151458", "Hållbar utveckling", 2021, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.adlibris.com%2Fse%2Fbok%2Fhallbar-utveckling---livskvalitet-beteende-och-teknik-9789144151458&psig=AOvVaw0m1oxt_x4E6gVfR-KKcUMB&ust=1614764579235000&source=images&cd=vfe&ved=0CA0QjhxqFwoTCJjEkvuoke8CFQAAAAAdAAAAABAJ");
        book.setAuthors(authors);
        book.setCategories(categories);
        bookDAO.create(book);
                
        condition = new Condition("Nyskick");
        conditionDAO.create(condition);

        listing = new Listing(new Date(), Money.of(50, "SEK"),"description",condition,acc,book);
        listingDAO.create(listing);
    }

    @After
    public void clean() {
        accountDAO.remove(acc);
        categoryDAO.remove(category);
        authorDAO.remove(author);
        authorDAO.remove(author2);
        bookDAO.remove(book);
        conditionDAO.remove(condition);
        listingDAO.remove(listing);
    }

    //Checks if the Username is correct.
    @Test
    public void checkThatGetAuthorsAsStringWorksCorrectly() {
        
    }
}
