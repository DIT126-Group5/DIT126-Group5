package com.group05.booksofbliss.model.service;

import com.group05.booksofbliss.model.dao.AccountDAO;
import com.group05.booksofbliss.model.entity.Account;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.money.MonetaryAmount;
import javax.transaction.Transactional;

@ApplicationScoped
public class BankService implements Serializable {

    @Inject
    private AccountDAO accountDAO;

    @Transactional
    public void performTransaction(Account from, Account to, MonetaryAmount amount) {
        // Sender has enough money
        if (from.getBalance().isLessThan(amount)) {
            throw new IllegalStateException("Sender does not have enough money to perform transaction");
        }

        from.withdrawFromBalance(amount);
        to.addToBalance(amount);

        accountDAO.update(from);
        accountDAO.update(to);
    }

    public void buyCredits(Account account, MonetaryAmount amount) {
        account.addToBalance(amount);
        accountDAO.update(account);
    }
}
