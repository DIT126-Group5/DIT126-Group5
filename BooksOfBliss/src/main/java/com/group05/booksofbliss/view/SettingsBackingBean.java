package com.group05.booksofbliss.view;

import com.group05.booksofbliss.model.entity.Account;
import com.group05.booksofbliss.security.Auth;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Named("settingsBackingBean")
@ViewScoped
public class SettingsBackingBean implements Serializable {

    @Inject
    private Auth auth;

    private Account account;

    @NotBlank
    private String password;

    @NotBlank
    private String newPassword;

    @PostConstruct
    private void init() {
        account = auth.getAccount();
    }

}
