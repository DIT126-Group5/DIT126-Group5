package com.group05.booksofbliss.model.dao;

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
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ListingDAOTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "com.group05.booksofbliss")
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private AuthorDAO authorDAO;

    @Inject
    private BookDAO bookDAO;

    @Inject
    private CategoryDAO categoryDAO;

    @Inject
    private ListingDAO listingDAO;

    @Inject
    private ConditionDAO conditionDAO;

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
    Listing listing2;
    Listing listing3;
    Long listingID;
    Long listingID2;
    Long listingID3;

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

        listing = new Listing(new Date(1615127000000L), Money.of(50, "SEK"), "description", condition, acc, book);
        listing2 = new Listing(new Date(1615126000000L), Money.of(50, "SEK"), "description", condition, acc, book);
        listing3 = new Listing(new Date(1615125000000L), Money.of(50, "SEK"), "description", condition, acc, book);

        listingDAO.create(listing);
        listingDAO.create(listing2);
        listingDAO.create(listing3);
        listingID = listing.getId();
        listingID2 = listing2.getId();
        listingID3 = listing3.getId();
    }

    @After
    public void clean() {
        listingDAO.remove(listing);
        listingDAO.remove(listing2);
        listingDAO.remove(listing3);
        bookDAO.remove(book);
        conditionDAO.remove(condition);
        categoryDAO.remove(category);
        authorDAO.remove(author);
        authorDAO.remove(author2);
        accountDAO.remove(acc);
    }

    @Test
    public void sortListingsByDateTest() {
        List<Listing> listings = new ArrayList<>();
        listings.add(listing);
        listings.add(listing2);
        listings.add(listing3);
        List<Listing> sorted = listingDAO.sortListingsByDate("newest");
        
        Assert.assertArrayEquals(listings.toArray(), sorted.toArray());
    }
}
