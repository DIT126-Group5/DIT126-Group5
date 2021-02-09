package com.group05.booksofbliss.model.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractDAO<T, P> {

    protected final Class<T> entityType;

    protected abstract EntityManager getEntityManager();

    protected JPAQueryFactory getQueryFactory() {
        return (new JPAQueryFactory(getEntityManager()));
    }

    //Insert
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    //Update
    public void update(T entity) {
        getEntityManager().merge(entity);
    }

    //Delete
    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    //Find by primary key
    public T findByPrimaryKey(P primaryKey) {
        return getEntityManager().find(entityType, primaryKey);
    }

    public List<T> findAll() {
        final CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityType));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public long count() {
        //queryDSL
        final CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery cq = builder.createQuery();
        final Root<T> rt = cq.from(entityType);
        cq.select(builder.count(rt));
        final Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult());
    }
}
