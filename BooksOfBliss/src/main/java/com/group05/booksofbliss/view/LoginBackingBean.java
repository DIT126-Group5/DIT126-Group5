package com.group05.booksofbliss.view;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named("loginBackingBean")
@ViewScoped
public class LoginBackingBean implements Serializable {

    @NotBlank(message = "Användarnamn är inte ifyllt")
    private String username;

    @NotBlank(message = "Lösenord är inte ifyllt")
    private String password;

}
