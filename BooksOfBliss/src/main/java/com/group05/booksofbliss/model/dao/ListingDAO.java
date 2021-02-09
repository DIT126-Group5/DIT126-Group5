package com.group05.booksofbliss.model.dao;

import com.group05.booksofbliss.model.entity.Account;
import com.group05.booksofbliss.model.entity.Listing;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

@Stateless
public class ListingDAO extends AbstractDAO<Listing, String> {
    @Getter
    @PersistenceContext(unitName = "bobDB")
    private EntityManager entityManager;
    
    public ListingDAO() {
        super(Listing.class);
    }
    
    private void search(String searchInput){
     
    }
}


