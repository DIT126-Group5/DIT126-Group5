package com.group05.booksofbliss.model.dao;

import com.group05.booksofbliss.model.entity.Book;
import com.group05.booksofbliss.model.entity.QBook;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

@Stateless
public class BookDAO extends AbstractDAO<Book, String> {
    @Getter @PersistenceContext (unitName = "bobDB")
    private EntityManager entityManager;

    public BookDAO() {
        super(Book.class);
    }
    
    public List<Book> findBooksMatchingTitle() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    public Book findByISBN(String isbn) {
        Book book = getQueryFactory()
                        .select(QBook.book)
                        .from(QBook.book)
                        .where(QBook.book.isbn.eq(isbn))
                        .fetchOne();
        
        return book;
    }
}
