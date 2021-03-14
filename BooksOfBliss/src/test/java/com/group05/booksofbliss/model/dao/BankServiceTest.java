package com.group05.booksofbliss.model.dao;

import com.group05.booksofbliss.Deployments;
import com.group05.booksofbliss.model.entity.Account;
import com.group05.booksofbliss.model.entity.attribute.Address;
import com.group05.booksofbliss.model.service.BankService;
import javax.inject.Inject;
import javax.money.MonetaryAmount;
import org.javamoney.moneta.Money;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class BankServiceTest {

    @Deployment
    public static WebArchive createDeployment() {
        return Deployments.defaultArchive();
    }

    @Inject
    private AccountDAO accountDAO;

    @Inject
    BankService bs;

    private Account account2 = new Account("username", "firstName", "lastName", "+46 72 985 32 12", "email@hotmail.com", "Password123!", new Address("Sannegarden", "45242", "Göteborg"), Money.of(0, "SEK"));
    private Account account = new Account("username2", "firstName", "lastName", "+46 72 985 32 12", "email@hotmail.com", "Password123!", new Address("Sannegarden", "45242", "Göteborg"), Money.of(100, "SEK"));

    @Before
    public void init() {
        accountDAO.create(account2);
        accountDAO.create(account);
    }

    @After
    public void clean() {
        accountDAO.remove(account2);
        accountDAO.remove(account);
    }

    //Checks if the Username is correct.
    @Test
    public void buyCreditsTest() {
        MonetaryAmount ma = accountDAO.find(account2.getUsername()).getBalance();

        Assert.assertEquals(ma, Money.of(0, "SEK"));
        bs.buyCredits(account2, Money.of(100, "SEK"));

        Assert.assertEquals(Money.of(100, "SEK"), accountDAO.find(account2.getUsername()).getBalance());
    }

    @Test
    public void performTransactionTest() {
        MonetaryAmount ma = accountDAO.find(account.getUsername()).getBalance();
        Assert.assertEquals(ma, Money.of(100, "SEK"));

        bs.performTransaction(account, account2, ma);

        Assert.assertEquals(Money.of(100, "SEK"), accountDAO.find(account2.getUsername()).getBalance());
        Assert.assertEquals(Money.of(0, "SEK"), accountDAO.find(account.getUsername()).getBalance());

        assertThrows(IllegalStateException.class, () -> {
            bs.performTransaction(account, account2, ma);
        });
    }

}
