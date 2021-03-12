package com.group05.booksofbliss.model.dao;

import com.group05.booksofbliss.model.entity.Author;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

@Stateless
public class AuthorDAO extends AbstractDAO<Author, String> {

    @Getter
    @PersistenceContext(unitName = "bobDB")
    private EntityManager entityManager;

    public AuthorDAO() {
        super(Author.class);
    }
}
