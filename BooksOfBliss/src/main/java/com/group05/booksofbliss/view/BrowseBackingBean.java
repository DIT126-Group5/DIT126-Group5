package com.group05.booksofbliss.view;

import com.group05.booksofbliss.model.dao.ListingDAO;
import com.group05.booksofbliss.model.entity.Listing;
import java.io.Serializable;
import java.util.List;
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

    public List<Listing> getListings() {
        if (searchQuery == null) {
            return listingDAO.getBuyableListingsSortedByDate();
        } else {
            return listingDAO.search(searchQuery);
        }
    }
}
