package com.group05.booksofbliss.model.dao;

import com.group05.booksofbliss.model.entity.Author;
import com.group05.booksofbliss.model.entity.Listing;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
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

    //Worst search method ever
    public List<Listing> search(String searchInput) {
        final String search = searchInput.toLowerCase();
        List<Listing> listings = getBuyableListingsSortedByDate();

        return listings.stream().filter(listing
                -> listing.getBook().getTitle().toLowerCase().contains(search)
                || listing.getBook().getIsbn().toLowerCase().contains(search)
                || listing.getBook().getAuthors().stream().map(Author::getName)
                        .anyMatch(author -> author.toLowerCase().contains(search)))
                .collect(Collectors.toList());
    }

    List<Listing> sortListingsByDate(List<Listing> listings) {
        Collections.sort(listings, new Comparator<Listing>() {
            public int compare(Listing l1, Listing l2) {
                return -l1.getDateTime().compareTo(l2.getDateTime());
            }
        });
        return listings;
    }

    public List<Listing> getBuyableListings() {
        List<Listing> listings = findAll();
        List<Listing> result = new ArrayList<>();

        for (Listing listing : listings) {
            if (listing.getPurchase() == null) {
                result.add(listing);
            }
        }

        return result;
    }

    public List<Listing> getBuyableListingsSortedByDate() {
        List<Listing> listings = getBuyableListings();
        sortListingsByDate(listings);
        return listings;
    }

}
