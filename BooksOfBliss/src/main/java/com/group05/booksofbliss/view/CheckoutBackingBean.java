    package com.group05.booksofbliss.view;

import com.group05.booksofbliss.model.dao.ListingDAO;
import com.group05.booksofbliss.model.entity.Listing;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import org.omnifaces.cdi.Param;

@Data
@Named(value = "checkoutBackingBean")
@ViewScoped
public class CheckoutBackingBean implements Serializable {

    @Inject
    @Param(pathIndex = 0)
    private Long listingId;

    @EJB
    private ListingDAO listingDAO;

    private String street;
    private String postalCode;
    private String city;

    public Listing getListing() {
        return listingDAO.find(listingId);
    }
}
