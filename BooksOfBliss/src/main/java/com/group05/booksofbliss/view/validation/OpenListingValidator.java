package com.group05.booksofbliss.view.validation;

import com.group05.booksofbliss.model.entity.Listing;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator
public class OpenListingValidator implements Validator<Listing> {

    @Override
    public void validate(FacesContext context, UIComponent component, Listing value) throws ValidatorException {
        if (context == null || component == null) {
            throw new NullPointerException();
        }

        if (value == null) {
            return;
        }

        if (value.getPurchase() != null) {
            throw new ValidatorException(new FacesMessage("This listing can no longer be bought"));
        }
    }

}
