package com.group05.booksofbliss.model.service;

import com.group05.booksofbliss.Deployments;
import com.group05.booksofbliss.model.entity.Account;
import com.group05.booksofbliss.model.entity.attribute.Address;
import javax.inject.Inject;
import org.javamoney.moneta.Money;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class AccountServiceTest {

    @Deployment
    public static WebArchive createDeployment() {
        return Deployments.defaultArchive();
    }

    Account account;

    @Inject
    AccountService accountService;

    @Before
    public void init() {
        account = new Account("username", "firstName", "lastName", "+46 72 985 32 12", "email@hotmail.com", "Password123!", new Address("Sannegarden", "45242", "Göteborg"), Money.of(10, "SEK"));
    }

    @Test
    public void setPasswordTest_shouldNotBeSameHash() {
        accountService.setPassword("Password111!", account);
        String oldHash = account.getPassword();
        accountService.setPassword("Password111!", account);
        Assert.assertNotEquals(oldHash, account.getPassword());
    }

    @Test(expected = IllegalArgumentException.class)
    public void setPasswordTest_invalidPassword_throwsIllegalArgumentException() {
        accountService.setPassword("pass", account);
    }

    @Test
    public void verifyPasswordTest_validVerifyPassword() {
        accountService.setPassword("Password111!", account);
        Assert.assertTrue(accountService.verifyPassword("Password111!", account));
    }

    @Test
    public void verifyPasswordTest_invalidVerifyPassword() {
        accountService.setPassword("Password111!", account);
        Assert.assertFalse(accountService.verifyPassword("Password123!", account));
    }
}
