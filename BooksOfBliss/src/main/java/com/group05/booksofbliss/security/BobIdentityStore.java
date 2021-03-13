package com.group05.booksofbliss.security;

import com.group05.booksofbliss.model.dao.AccountDAO;
import com.group05.booksofbliss.model.entity.Account;
import com.group05.booksofbliss.model.service.AccountService;
import java.util.Set;
import javax.inject.Inject;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import javax.security.enterprise.identitystore.IdentityStore;

public class BobIdentityStore implements IdentityStore {

    @Inject
    private AccountDAO accountDAO;

    @Inject
    private AccountService accountService;

    public CredentialValidationResult validate(UsernamePasswordCredential credential) {
        Account account = accountDAO.find(credential.getCaller());
        if (account == null) {
            return INVALID_RESULT;
        }

        if (accountService.verifyPassword(credential.getPasswordAsString(), account)) {
            return new CredentialValidationResult(credential.getCaller(), Set.of("member"));
        }
        return INVALID_RESULT;
    }
}
