package com.group05.booksofbliss.view;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Named("loginBackingBean")
@ViewScoped
public class LoginBackingBean implements Serializable {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
