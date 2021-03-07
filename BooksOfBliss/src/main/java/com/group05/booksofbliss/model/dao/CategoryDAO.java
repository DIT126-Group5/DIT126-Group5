package com.group05.booksofbliss.model.dao;

import com.group05.booksofbliss.model.entity.Book;
import com.group05.booksofbliss.model.entity.Category;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

@Stateless
public class CategoryDAO extends AbstractDAO<Category, String> {

    @Getter
    @PersistenceContext(unitName = "bobDB")
    private EntityManager entityManager;

    public CategoryDAO() {
        super(Category.class);
    }

    /*
    public List<Book> findBooksMatchingCategory(String name) {
        Category category = findByName(name);
        List<Book> bookList = category.getBooks();
        return bookList;
    }*/

    public Category findByName(String name) {
        return find(name);
    }
}
