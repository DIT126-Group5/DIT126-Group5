package com.group05.booksofbliss.view;

import com.group05.booksofbliss.model.dao.ListingDAO;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;


@Named
@ViewScoped
@Getter
public class PublishListingBackingBean implements Serializable {
    
    private ListingDAO listingDAO;
    // Id, date, price, description, account, condition
    @PostConstruct
    public void init(){
        //user = inloggadUser
    }
}
