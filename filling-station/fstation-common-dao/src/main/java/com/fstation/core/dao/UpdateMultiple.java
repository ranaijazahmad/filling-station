
package com.fstation.core.dao;

import java.util.List;

import com.fstation.core.dao.exception.DAOException;

@Deprecated
public interface UpdateMultiple<T> {

    /**
     * Update existing instances of a list of entities.
     * 
     * @param list
     *            of entities to update
     * @throws DAOException
     *             if a persistence error occurs
     */
    void update(List<T> entity) throws DAOException;
}
