package com.group05.booksofbliss.view;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Named("registerBackingBean")
@ViewScoped
public class RegisterBackingBean implements Serializable {

    @NotBlank
    @Size(min = 5, message = "Användarnamnet måste innehålla minst 5 karaktärer")
    private String username;

    @NotBlank(message = "Förnamn får inte vara tomt")
    private String firstname;

    @NotBlank(message = "Efternamn får inte vara tomt")
    private String lastname;

    @NotBlank(message = "Mobilnummer får inte vara tomt")
    private String phonenumber;

    @NotBlank(message = "E-post får inte vara tom")
    @Email(message = "E-post är inte giltig")
    private String email;

    @NonNull
    @NotBlank(message = "Gatans namn får inte vara tomt")
    private String street;

    @NonNull
    @NotBlank(message = "Postnumret får inte vara tomt")
    private String postalcode;

    @NonNull
    @NotBlank(message = "Stadens namn får inte vara tomt")
    private String city;

    @NotBlank
    private String password;

}
