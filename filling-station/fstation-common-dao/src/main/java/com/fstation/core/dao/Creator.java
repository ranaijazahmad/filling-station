
package com.fstation.core.dao;

import com.fstation.core.dao.exception.DAOException;

/**
 * Creation aspect of DAOs.
 * 
 * @param <T>
 *            the type we wish to persist
 */
@Deprecated
public interface Creator<T> {

    /**
     * Persist a new instance of entity. This is a low-level operation; checks
     * to verify that there are no existing, conflicting entities and that we
     * have the permissions to create should be done earlier on.
     * 
     * @param entity
     *            the entity to persist
     * @throws DAOException
     *             if a persistence error occurs
     */
    void create(T entity) throws DAOException;

}
