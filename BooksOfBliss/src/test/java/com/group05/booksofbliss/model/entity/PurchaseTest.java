package com.group05.booksofbliss.model.entity;

import com.group05.booksofbliss.model.entity.attribute.Address;
import java.util.Date;
import org.javamoney.moneta.Money;
import org.junit.Before;
import org.junit.Test;

public class PurchaseTest {

    private Purchase purchase;

    @Before
    public void init() {
        purchase = new Purchase(
                new Listing(new Date(1615127000000L), Money.of(50, "SEK"), "description",
                        new Condition("Nyskick"),
                        new Account("username", "firstname", "lastname", "0777777777", "namn@mail.se", "password", new Address("Street", "45163", "City"), Money.of(50, "SEK")),
                        new Book("1524861758", "Pride and Prejudice", 2021, "")),
                new Account("anotheruser", "firstname", "lastname", "0777777777", "namn@mail.se", "password", new Address("Street534", "345435", "gergehger"), Money.of(50, "SEK")),
                new Date(),
                new Address("Street534", "345435", "gergehger"));
    }

    @Test(expected = NullPointerException.class)
    public void setListing_givenNull_throwsNPE() {
        purchase.setListing(null);
    }

    @Test(expected = NullPointerException.class)
    public void setAccount_givenNull_throwsNPE() {
        purchase.setAccount(null);
    }

    @Test(expected = NullPointerException.class)
    public void setDateTime_givenNull_throwsNPE() {
        purchase.setDateTime(null);
    }

    @Test(expected = NullPointerException.class)
    public void setDeliveryAddress_givenNull_throwsNPE() {
        purchase.setDeliveryAddress(null);
    }

}
