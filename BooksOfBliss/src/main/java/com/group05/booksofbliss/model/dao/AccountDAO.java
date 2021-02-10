package com.group05.booksofbliss.model.dao;

import com.group05.booksofbliss.model.entity.Account;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

@Stateless
public class AccountDAO extends AbstractDAO<Account, String> {

    @Getter
    @PersistenceContext(unitName = "bobDB")
    private EntityManager entityManager;

    public AccountDAO() {
        super(Account.class);
    }

    public List<Account> findUsersMatchingName() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Account findByUsername(String username) {
        return find(username);
    }
    
}
