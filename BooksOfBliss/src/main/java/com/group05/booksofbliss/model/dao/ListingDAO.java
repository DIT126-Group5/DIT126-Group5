package com.group05.booksofbliss.model.dao;

import com.group05.booksofbliss.model.entity.Listing;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

@Stateless
public class ListingDAO extends AbstractDAO<Listing, Long> {

    @Getter
    @PersistenceContext(unitName = "bobDB")
    private EntityManager entityManager;

    public ListingDAO() {
        super(Listing.class);
    }

    //Not the best search method
    public List<Listing> search(String searchInput) {
        List<Listing> listings = findAll();
        List<Listing> searchedListings = new ArrayList<>();
        for (Listing listing : listings) {
            if (listing.getBook().getTitle().contains(searchInput)
                    || listing.getBook().getIsbn().contains(searchInput)) {
                searchedListings.add(listing);
            }
        }
        return searchedListings;
    }

    public List<Listing> sortListingsByDate(String order) {
        List<Listing> listings = findAll();

        Collections.sort(listings, new Comparator<Listing>() {
            public int compare(Listing l1, Listing l2) {
                return -l1.getDateTime().compareTo(l2.getDateTime());
            }
        });
        return listings;
    }
}
