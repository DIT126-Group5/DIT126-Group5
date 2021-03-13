package com.group05.booksofbliss.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractDAO<T, P> {

    protected final Class<T> entityType;

    protected abstract EntityManager getEntityManager();

    // Insert
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    // Update
    public void update(T entity) {
        getEntityManager().merge(entity);
    }

    // Delete
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    // Find by primary key
    public T find(P primaryKey) {
        return getEntityManager().find(entityType, primaryKey);
    }

    public List<T> findAll() {
        final CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityType));
        return getEntityManager().createQuery(cq).getResultList();
    }
}
