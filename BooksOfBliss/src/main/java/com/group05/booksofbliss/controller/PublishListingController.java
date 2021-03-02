package com.group05.booksofbliss.controller;

import com.group05.booksofbliss.model.entity.Condition;
import com.group05.booksofbliss.service.IsbnApi;
import com.group05.booksofbliss.view.PublishListingBackingBean;
import java.io.IOException;
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
    
    private double price;
    private String description;
    private Condition condition;
    private String isbn;
    
    public void searchIsbn() throws IOException, InterruptedException {
        //result = "Submitted values: " + price + ", " + choice;
        //System.out.println(result);
        System.out.println("isbn: " + isbn);
        String title = IsbnApi.getTitleFromApi(isbn);
        //String imgLink = IsbnApi.getImageUrl(isbn);
        System.out.println("title: " + title);
    }
}
