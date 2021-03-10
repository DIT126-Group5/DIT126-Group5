package com.group05.booksofbliss.model.service;

import com.group05.booksofbliss.model.dao.PurchaseDAO;
import com.group05.booksofbliss.model.entity.Account;
import com.group05.booksofbliss.model.entity.Listing;
import com.group05.booksofbliss.model.entity.Purchase;
import com.group05.booksofbliss.model.entity.attribute.Address;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class OrderService implements Serializable {

    @Inject
    private PurchaseDAO purchaseDAO;
    @Inject
    private BankService bankService;

    @Transactional
    public void orderListing(Listing listing, Account buyer, Address deliveryAddress) {
        if (listing.getPublishedBy().equals(buyer)) {
            //You can't buy your own listing
            throw new IllegalArgumentException("Buying your own listing is not allowed");
        }

        bankService.performTransaction(buyer, listing.getPublishedBy(), listing.getPrice());
        purchaseDAO.create(new Purchase(listing, buyer, new Date(), deliveryAddress));
    }
}
