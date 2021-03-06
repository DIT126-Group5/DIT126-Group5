package com.group05.booksofbliss.model.service;

import com.group05.booksofbliss.Deployments;
import com.group05.booksofbliss.model.dao.AccountDAO;
import com.group05.booksofbliss.model.dao.AuthorDAO;
import com.group05.booksofbliss.model.dao.BookDAO;
import com.group05.booksofbliss.model.dao.CategoryDAO;
import com.group05.booksofbliss.model.dao.ConditionDAO;
import com.group05.booksofbliss.model.dao.ListingDAO;
import com.group05.booksofbliss.model.dao.PurchaseDAO;
import com.group05.booksofbliss.model.entity.Account;
import com.group05.booksofbliss.model.entity.Author;
import com.group05.booksofbliss.model.entity.Book;
import com.group05.booksofbliss.model.entity.Category;
import com.group05.booksofbliss.model.entity.Condition;
import com.group05.booksofbliss.model.entity.Listing;
import com.group05.booksofbliss.model.entity.attribute.Address;
import com.group05.booksofbliss.model.service.OrderService;
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
import static org.junit.Assert.assertThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class OrderServiceTest {

    @Deployment
    public static WebArchive createDeployment() {
        return Deployments.defaultArchive();
    }

    @Inject
    private AuthorDAO authorDAO;

    @Inject
    private OrderService os;

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

    @Inject
    private PurchaseDAO purchaseDAO;

    private Account acc;
    private Account acc2;
    private Category category;
    private List<Category> categories;
    private Author author;
    private Author author2;
    private Author author3;
    private List<Author> authors;
    private List<Author> authors2;
    private Book book;
    private Book book2;
    private Condition condition;
    private Listing listing;
    private Listing listing2;

    @Before
    public void init() {
        acc = new Account("username", "firstname", "lastname", "0777777777", "namn@mail.se", "password", new Address("Street", "45163", "City"), Money.of(50, "SEK"));
        acc2 = new Account("username2", "firstname2", "lastname2", "0777777772", "namn2@mail.se", "password", new Address("Street", "45163", "City"), Money.of(50, "SEK"));

        accountDAO.create(acc);
        accountDAO.create(acc2);

        category = new Category("Hållbarhet");
        categoryDAO.create(category);
        categories = new ArrayList<>();
        categories.add(category);

        author = new Author("Håkan Gulliksson");
        author2 = new Author("Ulf Holmgren");
        author3 = new Author("Jane Austen");
        authorDAO.create(author);
        authorDAO.create(author2);
        authorDAO.create(author3);

        authors = new ArrayList<Author>();
        authors2 = new ArrayList<Author>();
        authors.add(author);
        authors.add(author2);
        authors2.add(author3);
        book = new Book("9789144151458", "Hållbar utveckling", 2021, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.adlibris.com%2Fse%2Fbok%2Fhallbar-utveckling---livskvalitet-beteende-och-teknik-9789144151458&psig=AOvVaw0m1oxt_x4E6gVfR-KKcUMB&ust=1614764579235000&source=images&cd=vfe&ved=0CA0QjhxqFwoTCJjEkvuoke8CFQAAAAAdAAAAABAJ");
        book2 = new Book("1524861758", "Pride and Prejudice", 2021, "");

        book.setAuthors(authors);
        book.setCategories(categories);
        book2.setAuthors(authors2);
        bookDAO.create(book);
        bookDAO.create(book2);

        condition = new Condition("Nyskick");
        conditionDAO.create(condition);

        listing = new Listing(new Date(1615127000000L), Money.of(50, "SEK"), "description", condition, acc, book);
        listing2 = new Listing(new Date(1615126000000L), Money.of(50, "SEK"), "description", condition, acc2, book);

        listingDAO.create(listing);
        listingDAO.create(listing2);
    }

    @After
    public void clean() {
        listingDAO.remove(listing);
        listingDAO.remove(listing2);
        bookDAO.remove(book);
        bookDAO.remove(book2);
        conditionDAO.remove(condition);
        categoryDAO.remove(category);
        authorDAO.remove(author);
        authorDAO.remove(author2);
        authorDAO.remove(author3);
        accountDAO.remove(acc);
        accountDAO.remove(acc2);
    }

    @Test
    public void orderListingTest() {

        Assert.assertNull(purchaseDAO.find(listing.getId()));
        os.orderListing(listing, acc2, acc2.getAddress());
        Assert.assertEquals(acc2, purchaseDAO.find(listing.getId()).getAccount());

        Assert.assertNull(purchaseDAO.find(listing2.getId()));

        assertThrows(IllegalArgumentException.class, () -> {
            os.orderListing(listing2, acc2, acc2.getAddress());
        });
        
        purchaseDAO.remove(purchaseDAO.find(listing.getId()));
    }

}
