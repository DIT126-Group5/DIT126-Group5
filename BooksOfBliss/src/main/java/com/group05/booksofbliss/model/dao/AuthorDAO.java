package com.group05.booksofbliss.model.dao;
import com.group05.booksofbliss.model.entity.Author;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

@Stateless
public class AuthorDAO extends AbstractDAO<Author, String> {
    @Getter @PersistenceContext (unitName = "bobDB")
    private EntityManager entityManager;

    public AuthorDAO() {
        super(Author.class);
    }
    
    public List<Author> findBooksMatchingTitle() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    public Author findById(String isbn) {
        Author author = getQueryFactory()
                        .select(QAuthor.author)
                        .from(QAuthor.author)
                        .where(QAuthor.author.isbn.eq(id))
                        .fetchOne();
        
        return author;
    }
}
