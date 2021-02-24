package com.group05.booksofbliss.view;

import com.group05.booksofbliss.model.dao.AccountDAO;
import com.group05.booksofbliss.model.entity.Account;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "accountDetails")
public class AccountBackingBean {

    @Inject
    private AccountDAO accountDAO;
    
    private Account account;

}
