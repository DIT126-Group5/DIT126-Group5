package com.group05.booksofbliss.model.dao;

import com.group05.booksofbliss.model.entity.Book;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

@Stateless
public class BookDAO extends AbstractDAO<Book, String> {

    @Getter
    @PersistenceContext(unitName = "bobDB")
    private EntityManager entityManager;

    public BookDAO() {
        super(Book.class);
    }
}
