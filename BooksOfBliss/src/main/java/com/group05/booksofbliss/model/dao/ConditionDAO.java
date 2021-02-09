package com.group05.booksofbliss.model.dao;

import com.group05.booksofbliss.model.entity.Condition;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;

@Stateless
public class ConditionDAO extends AbstractDAO<Condition, String> {
    @Getter
    @PersistenceContext(unitName = "bobDB")
    private EntityManager entityManager;

    public ConditionDAO() {
        super(Condition.class);
    }
}
