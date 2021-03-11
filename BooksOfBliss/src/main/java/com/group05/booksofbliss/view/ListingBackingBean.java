package com.group05.booksofbliss.view;

import com.group05.booksofbliss.model.entity.Listing;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import org.omnifaces.cdi.Param;

@Named("listingBackingBean")
@ViewScoped
public class ListingBackingBean implements Serializable {

    @Getter
    @Param(pathIndex = 0, converter = "#{listingConverter}")
    private Listing listing;
}
