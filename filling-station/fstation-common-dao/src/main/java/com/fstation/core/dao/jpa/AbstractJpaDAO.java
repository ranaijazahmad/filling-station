
package com.fstation.core.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.fstation.core.dao.DAO;
import com.fstation.core.dao.QueryBuilder;
import com.fstation.core.dao.exception.DAOException;
import com.google.inject.Provider;

public abstract class AbstractJpaDAO<T, Id> implements DAO<T, Id> {

    private final Class<? extends T> entityClass;
    private final Provider<EntityManager> emProvider;

    protected AbstractJpaDAO(final Class<? extends T> entityClass,
        final Provider<EntityManager> emProvider) {
        this.entityClass = entityClass;
        this.emProvider = emProvider;
    }

    public final void create(final T entity) throws DAOException {
        EntityManager em = emProvider.get();
        try {
            em.persist(entity);
            em.flush();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public final void create(final List<T> entities) throws DAOException {
        EntityManager em = emProvider.get();
        try {
            for (T entity : entities) {
                em.persist(entity);
            }
            em.flush();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public final T read(final Id id) throws DAOException {
        EntityManager em = emProvider.get();
        try {
            return em.find(entityClass, id);
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public final List<T> read(final List<Id> ids) throws DAOException {
        EntityManager em = emProvider.get();
        try {
            List<T> entities = new ArrayList<T>();
            for (Id id : ids) {
                entities.add(em.find(entityClass, id));
            }
            return entities;
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public final T update(final T entity) throws DAOException {
        EntityManager em = emProvider.get();
        T en;
        try {
            en = em.merge(entity);
            em.flush();
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return en;
    }

    public final void update(final List<T> entities) throws DAOException {
        EntityManager em = emProvider.get();
        try {
            for (T entity : entities) {
                em.merge(entity);
            }
            em.flush();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public final void delete(final Id id) throws DAOException {
        EntityManager em = emProvider.get();
        try {
            T e = em.getReference(entityClass, id);
            em.remove(e);
            em.flush();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public final void delete(final List<Id> ids) throws DAOException {
        EntityManager em = emProvider.get();
        try {
            for (Id id : ids) {
                T e = em.getReference(entityClass, id);
                em.remove(e);
            }
            em.flush();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    // turning checkstyle off because then it complains that this must be final,
    // when we most CERTAINLY override it in RelationshipDAOJPAPostgresImple
    // CHECKSTYLE:OFF DesignForExtension
    public QueryBuilder<T> namedQuery(final String queryName) throws DAOException {
        return namedQuery(queryName, entityClass);
    }

    public <S> QueryBuilder<S> namedQuery(final String queryName, final Class<? extends S> c) throws DAOException {
        EntityManager em = emProvider.get();
        Query q = em.createNamedQuery(queryName, c);
        return new QueryBuilderImpl<S>(q);
    }

    // // TODO Delete this
    // public QueryBuilder<T> namedQuery(final String queryName) throws
    // DAOException {
    // EntityManager em = emProvider.get();
    // Query q = em.createNamedQuery(queryName, entityClass);
    // return new QueryBuilderImpl<T>(q);
    // }
    // CHECKSTYLE:ON

    protected final Provider<EntityManager> getEmProvider() {
        return emProvider;
    }
}
