
package com.fstation.core.dao;

import java.util.List;

import com.fstation.core.dao.exception.DAOException;

/**
 * Retrieval aspect of DAOs
 * 
 * @param <T>
 *            the type we want retrieve
 * @param <Id>
 *            the identifier type of the type we want retrieve
 */
@Deprecated
public interface Reader<T, Id> {

    /**
     * Retrieve the item with the given id
     * 
     * @param id
     *            of the item we wish to retrieve
     * @return the item, or null if no such item exists
     * @throws DAOException
     *             if a persistence error occurs
     */
    T read(Id id) throws DAOException;

    /**
     * Retrieve all items of a given type from the persistence layer.
     * 
     * @return a list of all items or the empty list (never null)
     * @throws DAOException
     *             if a persistence error occurs
     */
    List<T> readAll() throws DAOException;

}
