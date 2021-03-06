package com.group05.booksofbliss.controller;

import com.group05.booksofbliss.model.dao.AccountDAO;
import com.group05.booksofbliss.model.service.AccountService;
import com.group05.booksofbliss.view.SettingsBackingBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

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
        if (accountService.verifyPassword(settingsBackingBean.getPassword(), settingsBackingBean.getAccount())) {
            setNewPassword();
            updateAccount();
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Det nuvarande lösenordet du har angett är inte korrekt.", null);
            facesContext.addMessage(null, facesMessage);
        }
    }

    private void setNewPassword() {
        try {
            accountService.setPassword(settingsBackingBean.getNewPassword(), settingsBackingBean.getAccount());
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Lösenordet har nu ändrats!", null);
            facesContext.addMessage(null, facesMessage);
        } catch (Exception e) {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Det nya lösenordet måste innehålla minst en versal, en siffra och vara minst 10 tecken.", null);
            facesContext.addMessage(null, facesMessage);
        }
    }
}
