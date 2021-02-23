package com.group05.booksofbliss.view.converter;

import com.group05.booksofbliss.model.dao.ListingDAO;
import com.group05.booksofbliss.model.entity.Listing;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

@Named("listingConverter")
@RequestScoped
public class ListingConverter implements Converter<Listing> {

    @Inject
    private ListingDAO listingDAO;

    @Override
    public Listing getAsObject(FacesContext context, UIComponent component, String value) {
        if (context == null || component == null) {
            throw new NullPointerException();
        }
        if (value == null) {
            return null;
        }

        try {
            return listingDAO.find(Long.parseLong(value));
        } catch (NumberFormatException nfe) {
            throw new ConverterException("Failed to convert string into " + Listing.class.getName(), nfe);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Listing value) {
        if (context == null || component == null) {
            throw new NullPointerException();
        }
        if (value == null) {
            return "";
        }

        return value.getId().toString();
    }
}
