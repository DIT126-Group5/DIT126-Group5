package com.group05.booksofbliss.security;

import com.group05.booksofbliss.model.dao.AccountDAO;
import com.group05.booksofbliss.model.entity.Account;
import java.io.Serializable;
import java.security.Principal;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.SecurityContext;

@Named("auth")
@SessionScoped
public class Auth implements Serializable {

    @Inject
    private SecurityContext securityContext;

    @Inject
    private AccountDAO accountDao;

    private Account account;

    public Account getAccount() {
        if (account == null) {
            Principal principal = securityContext.getCallerPrincipal();
            if (principal != null) {
                account = accountDao.find(principal.getName());
            }
        }
        return account;
    }
}
