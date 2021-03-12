package com.group05.booksofbliss.controller;

import com.group05.booksofbliss.model.dao.AccountDAO;
import com.group05.booksofbliss.model.service.AccountService;
import com.group05.booksofbliss.view.SettingsBackingBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@Named("settingsController")
@RequestScoped
public class SettingsController {

    @Inject
    private SettingsBackingBean settingsBackingBean;

    @Inject
    private AccountService accountService;

    @Inject
    private AccountDAO accountDAO;

    @Inject
    private FacesContext facesContext;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    public void updateAccount() {
        try {
            accountDAO.update(settingsBackingBean.getAccount());
        } catch (Exception e) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Det gick inte att uppdatera kontot.", null);
            facesContext.addMessage(null, facesMessage);
        }
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
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Lösenordet du har angett är inte korrekt.", null);
            facesContext.addMessage(null, facesMessage);
        }
    }

    private void setNewPassword() {
        try {
            accountService.setPassword(settingsBackingBean.getNewPassword(), settingsBackingBean.getAccount());
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Lösenordet har nu ändrats!", null);
            facesContext.addMessage(null, facesMessage);
        } catch (Exception e) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Lösenordet du har angett är inte giltig.", null);
            facesContext.addMessage(null, facesMessage);
        }
    }
}
