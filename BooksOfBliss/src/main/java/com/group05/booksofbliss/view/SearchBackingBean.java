package com.group05.booksofbliss.view;

import com.group05.booksofbliss.model.dao.ListingDAO;
import com.group05.booksofbliss.model.entity.Listing;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import lombok.Data;

@Data
@Named(value = "searchBackingBean")
@RequestScoped
public class SearchBackingBean implements Serializable {

    @EJB
    private ListingDAO listingDAO;
    private List<Listing> searchedListings = new ArrayList<>();
    
    public List<Listing> getListings() {
        return listingDAO.findAll();
    }
    
    public Listing getListing(long id) {
        return listingDAO.find(id);
    }
    
    public List<Listing> search(String searchInput) {
        List<Listing> listings = listingDAO.findAll();
        
        for (Listing listing : listings) {
            if (listing.getBook().getTitle().contains(searchInput)
                    || listing.getBook().getIsbn().contains(searchInput)) {
                searchedListings.add(listing);
            }
        }
        return searchedListings;
    }

}
