package com.group05.booksofbliss.security;

import com.group05.booksofbliss.Deployments;
import com.group05.booksofbliss.model.dao.AccountDAO;
import com.group05.booksofbliss.model.entity.Account;
import com.group05.booksofbliss.model.entity.attribute.Address;
import com.group05.booksofbliss.model.service.AccountService;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import org.javamoney.moneta.Money;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class BobIdentityStoreTest {

    @Deployment
    public static WebArchive createDeployment() {
        return Deployments.defaultArchive();
    }

    private static final String USERNAME = "testusernameperson4565464";
    private static final String PASSWORD = "Mypassword123!";

    @Inject
    private AccountDAO accountDAO;

    @Inject
    private AccountService accountService;

    @Inject
    private BobIdentityStore identityStore;

    private Account acc;

    @Before
    public void init() {
        acc = new Account(USERNAME, "firstName", "lastName", "+46 72 985 32 12", "email@hotmail.com", "", new Address("Sannegarden", "45242", "Göteborg"), Money.of(10, "SEK"));
        accountService.setPassword(PASSWORD, acc);
        accountDAO.create(acc);
    }

    @After
    public void cleanup() {
        accountDAO.remove(acc);
    }

    @Test
    public void testWithValidCredentials() {
        Credential credential = new UsernamePasswordCredential(USERNAME, PASSWORD);
        CredentialValidationResult result = identityStore.validate(credential);

        assertEquals(CredentialValidationResult.Status.VALID, result.getStatus());
        assertEquals(USERNAME, result.getCallerPrincipal().getName());
    }

    @Test
    public void testWithInvalidPassword() {
        Credential credential = new UsernamePasswordCredential(USERNAME, "InvalidPassword");
        CredentialValidationResult result = identityStore.validate(credential);

        assertEquals(CredentialValidationResult.Status.INVALID, result.getStatus());
    }

    @Test
    public void testWithInvalidUsername() {
        Credential credential = new UsernamePasswordCredential("InvalidUsername", PASSWORD);
        CredentialValidationResult result = identityStore.validate(credential);

        assertEquals(CredentialValidationResult.Status.INVALID, result.getStatus());
    }
}
