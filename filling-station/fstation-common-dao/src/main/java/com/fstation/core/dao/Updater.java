
package com.fstation.core.dao;

import com.fstation.core.dao.exception.DAOException;

/**
 * Update/modification aspect of DAOs
 * 
 * @param <T>
 *            the type we wish to update
 */
@Deprecated
public interface Updater<T> {

    /**
     * Update the given entity in the persistence layer. Note that this is a
     * low-level operation; checks to ensure that the entity exists and that we
     * have permission to update it should be done at a higher-level.
     * 
     * @param entity
     * @throws DAOException
     */
    void update(T entity) throws DAOException;

}
