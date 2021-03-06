package com.group05.booksofbliss.model.service;

import com.group05.booksofbliss.model.entity.Account;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

@ApplicationScoped
public class AccountService {

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    public void setPassword(String password, Account account) {
        if (!account.isValidPassword(password)) {
            throw new IllegalArgumentException("Lösenordet måste innehålla minst en versal, en siffra och vara minst 10 tecken.");
        }
        account.setPassword(passwordHash.generate(password.toCharArray()));
    }

    public boolean verifyPassword(String password, Account account) {
        return passwordHash.verify(password.toCharArray(), account.getPassword());
    }
}
