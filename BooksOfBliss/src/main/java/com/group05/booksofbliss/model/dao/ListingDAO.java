package com.group05.booksofbliss.model.dao;

import com.group05.booksofbliss.model.entity.Listing;
import com.group05.booksofbliss.model.entity.QListing;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

@Stateless
public class ListingDAO extends AbstractDAO<Listing, String> {
    @Getter
    @PersistenceContext(unitName = "bobDB")
    private EntityManager entityManager;
    
    public ListingDAO() {
        super(Listing.class);
    }
    
    //Not the best search method
    private List<Listing> search(String searchInput){
        List<Listing> listings = findAll();
        List<Listing> searchedListings = new ArrayList<>();
        for(Listing listing : listings){
            if(listing.getBook().getTitle().contains(searchInput) || 
                    listing.getBook().getIsbn().contains(searchInput))
                searchedListings.add(listing);
        }
        return searchedListings;
    }
}


