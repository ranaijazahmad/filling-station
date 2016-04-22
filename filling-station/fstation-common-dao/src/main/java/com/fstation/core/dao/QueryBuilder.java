
package com.fstation.core.dao;

import java.util.List;
import java.util.Map;

import com.fstation.core.dao.exception.DAOException;

public interface QueryBuilder<T> {

    QueryBuilder<T> setParameter(String name, Object value);

    QueryBuilder<T> setParameter(int paramNum, Object value);

    T asSingle() throws DAOException;

    List<T> asList() throws DAOException;

    <K> Map<K, T> asMap(KeyMapper<K, T> mapper);

    <K> Map<K, T> asTreeMap(KeyMapper<K, T> mapper);

}
