
package com.fstation.core.dao;

import com.fstation.core.dao.exception.DAOException;

/**
 * Deletion aspect of DAOs
 * 
 * @param <Id>
 *            identifier type of the item we wish to remove from persistence
 */
public interface Deleter<Id> {

    /**
     * Delete an entity from the persistence layer. Note that this is a
     * low-level operation; checks to ensure that the entity actually exists and
     * that we have the rights to delete it belong at a higher level.
     * 
     * @param id
     *            unique identifier for an entity
     * @throws DAOException
     *             if a persistence error occurs
     */
    void delete(Id id) throws DAOException;

}
