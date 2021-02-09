package com.group05.booksofbliss.model.dao;

import com.group05.booksofbliss.model.entity.Category;
import com.group05.booksofbliss.model.entity.Book;
import com.group05.booksofbliss.model.entity.QCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

@Stateless
public class CategoryDAO extends AbstractDAO<Book, String> {
    @Getter @PersistenceContext (unitName = "bobDB")
    private EntityManager entityManager;

    public CategoryDAO() {
        super(Book.class);
    }
    
    public List<Book> findBooksMatchingCategory() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    public Category findByName(String name) {
        Category category = getQueryFactory()
                        .select(QCategory.category)
                        .from(QCategory.category)
                        .where(QCategory.category.name.eq(name))
                        .fetchOne();
        
        return category;
    }
}
