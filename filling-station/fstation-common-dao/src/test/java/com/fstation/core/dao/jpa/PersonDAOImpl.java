package com.fstation.core.dao.jpa;

import javax.persistence.EntityManager;

import com.fstation.core.dao.jpa.AbstractJpaDAO;
import com.fstation.core.dao.jpa.entities.Person;
import com.google.inject.Provider;

public class PersonDAOImpl extends AbstractJpaDAO<Person, String>
    implements PersonDAO {

    public PersonDAOImpl(final Provider<EntityManager> emProvider) {
        super(Person.class, emProvider);
    }
}
