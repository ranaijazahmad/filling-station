
package com.fstation.core.dao;

import java.util.List;

import com.fstation.core.dao.exception.DAOException;

public interface DAO<T, Id> {

    void create(T entity) throws DAOException;

    void create(List<T> entities) throws DAOException;

    T read(Id id) throws DAOException;

    List<T> read(List<Id> ids) throws DAOException;

    T update(T entity) throws DAOException;

    void update(List<T> entities) throws DAOException;

    void delete(Id id) throws DAOException;

    void delete(List<Id> ids) throws DAOException;

    QueryBuilder<T> namedQuery(String queryName) throws DAOException;

    // QueryBuilder<T> namedQuery(String queryName) throws DAOException;

}
