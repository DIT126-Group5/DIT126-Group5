package com.group05.booksofbliss.security;

import com.group05.booksofbliss.model.dao.AccountDAO;
import com.group05.booksofbliss.model.entity.Account;
import java.util.Set;
import javax.inject.Inject;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

public class BobIdentityStore implements IdentityStore {

    @Inject
    private AccountDAO accountDAO;

    @Inject
    private Pbkdf2PasswordHash passwordHash;

    public CredentialValidationResult validate(UsernamePasswordCredential credential) {
        // TODO: Remove
        System.out.println("HAAAAAAAAAAAAAAAAASH: " + passwordHash.generate(credential.getPassword().getValue()));

        Account account = accountDAO.find(credential.getCaller());
        if (account == null) {
            return INVALID_RESULT;
        }

        if (passwordHash.verify(credential.getPassword().getValue(), account.getPassword())) {
            return new CredentialValidationResult(credential.getCaller(), Set.of("member"));
        }
        return INVALID_RESULT;
    }
}
