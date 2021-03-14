package com.group05.booksofbliss.model.entity;

import com.group05.booksofbliss.model.entity.attribute.Address;
import java.util.Date;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;

public class ListingTest {

    private Listing listing;

    @Before
    public void init() {
        listing = new Listing(new Date(1615127000000L), Money.of(50, "SEK"), "description",
                new Condition("Nyskick"),
                new Account("username", "firstname", "lastname", "0777777777", "namn@mail.se", "password", new Address("Street", "45163", "City"), Money.of(50, "SEK")),
                new Book("1524861758", "Pride and Prejudice", 2021, ""));
    }

    @Test(expected = NullPointerException.class)
    public void setPrice_givenNull_throwsNPE() {
        listing.setPrice(null);
    }

    @Test(expected = NullPointerException.class)
    public void setDateTime_givenNull_throwsNPE() {
        listing.setDateTime(null);
    }

    @Test(expected = NullPointerException.class)
    public void setDescription_givenNull_throwsNPE() {
        listing.setDescription(null);
    }

    @Test(expected = NullPointerException.class)
    public void setCondition_givenNull_throwsNPE() {
        listing.setCondition(null);
    }

    @Test(expected = NullPointerException.class)
    public void setPublishedBy_givenNull_throwsNPE() {
        listing.setPublishedBy(null);
    }

    @Test(expected = NullPointerException.class)
    public void setBook_givenNull_throwsNPE() {
        listing.setBook(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setPrice_givenNegativeAmount_throwsIllegalArgumentException() {
        listing.setPrice(Money.of(-1, "SEK"));
    }
}
