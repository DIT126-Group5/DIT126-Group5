package com.group05.booksofbliss.controller;

import com.group05.booksofbliss.model.dao.AccountDAO;
import com.group05.booksofbliss.model.entity.Account;
import com.group05.booksofbliss.model.entity.attribute.Address;
import com.group05.booksofbliss.model.service.AccountService;
import com.group05.booksofbliss.view.RegisterBackingBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import org.javamoney.moneta.Money;

@FacesConfig
@Data
@Named("registerController")
@RequestScoped
public class RegisterController {

    @Inject
    RegisterBackingBean registerBackingBean;

    @Inject
    AccountDAO accountDAO;

    @Inject
    AccountService accountService;

    public void createAccount() {
        Account acc = new Account(registerBackingBean.getUsername(),
                registerBackingBean.getFirstname(),
                registerBackingBean.getLastname(),
                registerBackingBean.getPhonenumber(),
                registerBackingBean.getEmail(),
                registerBackingBean.getPassword(),
                new Address(registerBackingBean.getStreet(), registerBackingBean.getPostalcode(), registerBackingBean.getCity()),
                Money.of(0, "SEK")
        );
        accountService.setPassword(registerBackingBean.getPassword(), acc);
        accountDAO.create(acc);
    }

}
