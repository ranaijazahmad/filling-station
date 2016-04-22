
package com.fstation.core.dao;

import java.util.List;

import com.fstation.core.dao.exception.DAOException;

@Deprecated
public interface CreateMultiple<T> {

    /**
     * Persist new instances of a list entity. This is a low-level operation;
     * checks to verify that there are no existing, conflicting entities and
     * that we have the permissions to create should be done earlier on.
     * 
     * @param list
     *            entity to persist
     * @throws DAOException
     *             if a persistence error occurs
     */
    void create(List<T> entity) throws DAOException;
}
