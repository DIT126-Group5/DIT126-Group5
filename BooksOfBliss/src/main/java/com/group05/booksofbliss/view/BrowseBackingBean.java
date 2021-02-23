package com.group05.booksofbliss.view;

import com.group05.booksofbliss.model.dao.ListingDAO;
import com.group05.booksofbliss.model.entity.Account;
import com.group05.booksofbliss.model.entity.Author;
import com.group05.booksofbliss.model.entity.Book;
import com.group05.booksofbliss.model.entity.Category;
import com.group05.booksofbliss.model.entity.Condition;
import com.group05.booksofbliss.model.entity.Listing;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import org.omnifaces.cdi.Param;

@Data
@Named(value = "browseBackingBean")
@ViewScoped
public class BrowseBackingBean implements Serializable {

    @EJB
    private ListingDAO listingDAO;

    public List<Listing> getListings() {
        return listingDAO.findAll();
    }
    
    public Listing getListing(long id) {
        return listingDAO.find(id);
    }
}
