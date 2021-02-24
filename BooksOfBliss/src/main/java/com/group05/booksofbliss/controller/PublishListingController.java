package com.group05.booksofbliss.controller;

import com.group05.booksofbliss.model.entity.Condition;
import com.group05.booksofbliss.view.PublishListingBackingBean;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;

@Data
@ViewScoped
@Named
public class PublishListingController implements Serializable {
    //Denna klass skall ta information från formulären från xhtml-filen, 
    //och sedan skicka detta vidare till BackingBean, som i sin tur 
    //anropar create i ListingDAO.
    @Inject
    private PublishListingBackingBean publishListingBackingBean;
    //private double price;
    //private String description;
    //private Condition condition;
    
    public void publish(double price, String description, Condition condition){
        publishListingBackingBean.setPrice(price);
        publishListingBackingBean.setDescription(description);
        publishListingBackingBean.setCondition(condition);
    }
}
