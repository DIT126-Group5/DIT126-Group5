package com.group05.booksofbliss.view;

import com.group05.booksofbliss.model.dao.ListingDAO;
import com.group05.booksofbliss.model.entity.Listing;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.Param;

@Named("browseBackingBean")
@ViewScoped
public class BrowseBackingBean implements Serializable {

    @Inject
    private ListingDAO listingDAO;

    @Param(name = "q")
    private String searchQuery;

    private String sortQuery;

    private List<Listing> listings;

    public List<Listing> getListings() {
        if (listings == null) {
            if (searchQuery == null) {
                listings = listingDAO.getBuyableListingsSortedByDate();
            } else {
                listings = listingDAO.search(searchQuery);
            }
        }
        if (sortQuery != null) {
            if (sortQuery.equals("Date")) {
                sortByDate();
            } else if (sortQuery.equals("Price")) {
                sortByPrice();
            }
        }

        return listings;
    }

    private void sortByDate() {
        Collections.sort(listings, new Comparator<Listing>() {
            public int compare(Listing l1, Listing l2) {
                return -l1.getDateTime().compareTo(l2.getDateTime());
            }
        });
    }

    private void sortByPrice() {
        Collections.sort(listings, new Comparator<Listing>() {
            public int compare(Listing l1, Listing l2) {
                return l1.getPrice().compareTo(l2.getPrice());
            }
        });
    }

    public void setDateSort() {
        sortQuery = "Date";
    }

    public void setPriceSort() {
        sortQuery = "Price";
    }

}
