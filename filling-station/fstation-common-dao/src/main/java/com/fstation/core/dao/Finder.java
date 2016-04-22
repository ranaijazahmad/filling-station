
package com.fstation.core.dao;

import java.util.List;

import com.fstation.core.dao.exception.DAOException;

/**
 * Search aspect of DAOs.
 * 
 * Typically this would be used to optimize lookups, driving more down to the
 * underlying database/persistence layer.
 * 
 * @param <T>
 *            the type we wish to find
 * @param <C>
 *            search criteria type
 */
@Deprecated
public interface Finder<T, C> {

    /**
     * Find a list of items matching the given criteria. Typically this would be
     * used to optimize lookups, driving more down to the underlying
     * database/persistence layer.
     * 
     * @param criteria
     *            search criteria
     * @return list of matching items, or empty list (never null)
     * @throws DAOException
     *             if a persistence error occurs
     */
    List<T> find(C criteria) throws DAOException;

}
