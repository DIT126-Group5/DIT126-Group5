package com.group05.booksofbliss.model.dao;

import com.group05.booksofbliss.model.database.dao.key.UserReviewPK;
import com.group05.booksofbliss.model.entity.UserReview;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

@Stateless
public class UserReviewDAO extends AbstractDAO<UserReview, UserReviewPK> {

    @Getter
    @PersistenceContext(unitName = "bobDB")
    private EntityManager entityManager;

    public UserReviewDAO() {
        super(UserReview.class);
    }
}
