package com.group05.booksofbliss.view;

import com.group05.booksofbliss.model.entity.Account;
import com.group05.booksofbliss.security.Auth;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Named("settingsBackingBean")
@ViewScoped
public class SettingsBackingBean implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Inject
    private Auth auth;

    @Setter(AccessLevel.NONE)
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
