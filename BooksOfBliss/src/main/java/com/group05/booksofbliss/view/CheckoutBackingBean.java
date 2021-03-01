package com.group05.booksofbliss.view;

import com.group05.booksofbliss.model.entity.Listing;
import com.group05.booksofbliss.view.validation.OpenListingValidator;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.omnifaces.cdi.Param;

@Data
@Named("checkoutBackingBean")
@ViewScoped
public class CheckoutBackingBean implements Serializable {

    @Param(pathIndex = 0, converter = "#{listingConverter}", validatorClasses = {OpenListingValidator.class})
    private Listing listing;

    @NotBlank(message = "En gata måste fyllas i")
    private String street;
    @NotBlank(message = "Ett postnummer måste fyllas i")
    private String postalCode;
    @NotBlank(message = "En stad måste fyllas i")
    private String city;
}
