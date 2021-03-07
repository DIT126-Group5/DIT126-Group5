package com.group05.booksofbliss.controller;

import com.group05.booksofbliss.model.dao.AccountDAO;
import com.group05.booksofbliss.model.service.AccountService;
import com.group05.booksofbliss.view.SettingsBackingBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import lombok.Data;

@FacesConfig
@Data
@Named("settingsController")
@RequestScoped
public class SettingsController {

    @Inject
    SettingsBackingBean settingsBackingBean;

    @Inject
    AccountService accountService;

    @Inject
    AccountDAO accountDAO;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    public void updateAccount() {
        accountDAO.update(settingsBackingBean.getAccount());
    }

    public void changePassword() {
        //Check if password is the same as for the account in the database?
        System.out.println("changePassword");
        if (accountService.verifyPassword(settingsBackingBean.getPassword(), settingsBackingBean.getAccount())) {
            System.out.println("beforeSetPassword");
            setNewPassword();
            System.out.println("beforeUpdateAccount" + settingsBackingBean.getAccount());
            updateAccount();
            System.out.println("afterUpdateAccount" + settingsBackingBean.getAccount());
        }
        //else Error: "wrong password"
    }

    public void setNewPassword() {
        accountService.setPassword(settingsBackingBean.getNewPassword(), settingsBackingBean.getAccount());
    }
}
