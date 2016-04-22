
package com.fstation.core.dao;

import java.util.Map;

import com.fstation.core.dao.exception.DAOException;

/**
 * Search aspect of DAOs.
 * 
 * Typically this would be used to optimize lookups, driving more down to the
 * underlying database/persistence layer.
 * 
 * @param <T>
 *            the key type we wish to find
 * @param <T>
 *            the type we wish to find
 * @param <C>
 *            search criteria type
 */
@Deprecated
public interface MapFinder<K, T, C> {

    Map<K, T> find(C criteria) throws DAOException;

}
