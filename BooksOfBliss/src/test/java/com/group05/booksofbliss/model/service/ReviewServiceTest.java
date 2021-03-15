package com.group05.booksofbliss.model.service;

import com.group05.booksofbliss.Deployments;
import com.group05.booksofbliss.model.dao.AccountDAO;
import com.group05.booksofbliss.model.dao.AuthorDAO;
import com.group05.booksofbliss.model.dao.BookDAO;
import com.group05.booksofbliss.model.dao.CategoryDAO;
import com.group05.booksofbliss.model.dao.ConditionDAO;
import com.group05.booksofbliss.model.dao.ListingDAO;
import com.group05.booksofbliss.model.dao.PurchaseDAO;
import com.group05.booksofbliss.model.dao.UserReviewDAO;
import com.group05.booksofbliss.model.database.dao.key.UserReviewPK;
import com.group05.booksofbliss.model.entity.Account;
import com.group05.booksofbliss.model.entity.Author;
import com.group05.booksofbliss.model.entity.Book;
import com.group05.booksofbliss.model.entity.Condition;
import com.group05.booksofbliss.model.entity.Listing;
import com.group05.booksofbliss.model.entity.Purchase;
import com.group05.booksofbliss.model.entity.attribute.Address;
import java.util.Date;
import java.lang.IllegalArgumentException;
import java.util.Arrays;
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
public class ReviewServiceTest {

    @Deployment
    public static WebArchive createDeployment() {
        return Deployments.defaultArchive();
    }

    @Inject
    private AuthorDAO authorDAO;

    @Inject
    private BookDAO bookDAO;

    @Inject
    private ListingDAO listingDAO;

    @Inject
    private ConditionDAO conditionDAO;

    @Inject
    private AccountDAO accountDAO;

    @Inject
    private PurchaseDAO purchaseDAO;

    @Inject
    private ReviewService reviewService;

    @Inject
    private UserReviewDAO userReviewDAO;

    Account acc;
    Account acc2;
    Account acc3;
    Purchase purchase;
    Purchase purchase2;
    Purchase purchase3;
    Listing listing;
    Listing listing2;
    Listing listing3;
    Book book;
    Author author;
    Condition condition;

    @Before
    public void init() {
        acc = new Account("user1", "u1", "ln1", "07777778", "mail@test.se", "Password123!", new Address("Gatan", "12345", "Göteborg"), Money.of(50, "SEK"));
        acc2 = new Account("user2", "u2", "ln2", "07777777", "mail2@test.se", "Password123!", new Address("Gatan", "12345", "Göteborg"), Money.of(50, "SEK"));
        acc3 = new Account("user3", "u3", "ln3", "07777776", "mail3@test.se", "Password123!", new Address("Gatan", "12345", "Göteborg"), Money.of(50, "SEK"));
        accountDAO.create(acc);
        accountDAO.create(acc2);
        accountDAO.create(acc3);

        author = new Author("Jane Austen");
        authorDAO.create(author);

        condition = new Condition("Bra skick");
        conditionDAO.create(condition);

        book = new Book("1234567891", "Fake book", 2020, "");
        bookDAO.create(book);

        listing = new Listing(new Date(1615127000000L), Money.of(50, "SEK"), "description", condition, acc, book);
        listing2 = new Listing(new Date(1615128000000L), Money.of(50, "SEK"), "description", condition, acc, book);
        listing3 = new Listing(new Date(1615124000000L), Money.of(50, "SEK"), "description", condition, acc, book);
        listingDAO.create(listing);
        listingDAO.create(listing2);
        listingDAO.create(listing3);

        purchase = new Purchase(listing, acc2, new Date(1615128000000L), new Address("Street", "12342", "City"));
        purchase2 = new Purchase(listing2, acc2, new Date(1615129000000L), new Address("Street", "12342", "City"));
        purchase3 = new Purchase(listing3, acc3, new Date(1615125000000L), new Address("Street", "12342", "City"));

        purchaseDAO.create(purchase);
        purchaseDAO.create(purchase2);
        purchaseDAO.create(purchase3);

    }

    @After
    public void clean() {
        purchaseDAO.remove(purchase);
        purchaseDAO.remove(purchase2);
        purchaseDAO.remove(purchase3);
        listingDAO.remove(listing);
        listingDAO.remove(listing2);
        listingDAO.remove(listing3);
        bookDAO.remove(book);
        conditionDAO.remove(condition);
        authorDAO.remove(author);
        accountDAO.remove(acc);
        accountDAO.remove(acc2);
        accountDAO.remove(acc3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void reviewPurchaseTooHighRating() {
        reviewService.reviewPurchase(purchase, "Du förtjänar mer än en femma!", 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void reviewPurchaseTooLowRating() {
        reviewService.reviewPurchase(purchase, "Så missnöjd...", 0);
    }

    @Test
    public void reviewPurchaseReputationUpdate() {
        reviewService.reviewPurchase(purchase, "Jättenöjd!", 5);
        Assert.assertEquals(5.0, accountDAO.find(acc.getUsername()).getReputation(), 0.0);
        userReviewDAO.remove(userReviewDAO.find(new UserReviewPK(acc2.getUsername(), acc.getUsername())));
    }

    @Test
    public void reviewPurchaseUpdateOldReview() {
        reviewService.reviewPurchase(purchase, "Toppen!", 4);
        reviewService.reviewPurchase(purchase2, "Njaa, du var bättre förr...", 2);
        try {
            Assert.assertEquals("Njaa, du var bättre förr...", accountDAO.find(acc.getUsername()).getReviewsReceived().get(0).getComment());
            Assert.assertEquals(2.0, accountDAO.find(acc.getUsername()).getReputation(), 2.0);
        } finally {
            userReviewDAO.remove(userReviewDAO.find(new UserReviewPK(acc2.getUsername(), acc.getUsername())));
        }
    }

    @Test
    public void reviewPurchaseAverageReputation() {
        reviewService.reviewPurchase(purchase, "Bästa någonsin", 5);
        reviewService.reviewPurchase(purchase3, "Sämsta någonsin", 1);
        try {
            Assert.assertEquals(3.0, accountDAO.find(acc.getUsername()).getReputation(), 0.0);
        } finally {
            userReviewDAO.remove(userReviewDAO.find(new UserReviewPK(acc2.getUsername(), acc.getUsername())));
            userReviewDAO.remove(userReviewDAO.find(new UserReviewPK(acc3.getUsername(), acc.getUsername())));
        }
    }
}
