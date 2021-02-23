package com.group05.booksofbliss.model.dao;

import com.group05.booksofbliss.model.entity.Listing;
import com.group05.booksofbliss.model.entity.Purchase;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

@Stateless
public class PurchaseDAO extends AbstractDAO<Purchase, Listing> {

    @Getter
    @PersistenceContext(unitName = "bobDB")
    private EntityManager entityManager;

    public PurchaseDAO() {
        super(Purchase.class);
    }
}
