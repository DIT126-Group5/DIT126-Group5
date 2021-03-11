package com.group05.booksofbliss.controller;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("logoutController")
@RequestScoped
public class LogoutController implements Serializable {

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "logged_out";
    }

}
