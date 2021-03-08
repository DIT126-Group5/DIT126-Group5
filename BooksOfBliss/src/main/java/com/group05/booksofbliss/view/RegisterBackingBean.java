package com.group05.booksofbliss.view;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Named("registerBackingBean")
@ViewScoped
public class RegisterBackingBean implements Serializable {

    @NotBlank
    private String username;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    private String phonenumber;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String street;

    @NotBlank
    private String postalcode;

    @NotBlank
    private String city;

    @NotBlank
    private String password;

}
