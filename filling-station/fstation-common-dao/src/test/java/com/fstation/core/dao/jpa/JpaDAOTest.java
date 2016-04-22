package com.fstation.core.dao.jpa;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fstation.core.dao.exception.DAOException;
import com.fstation.core.dao.jpa.entities.Person;
import com.google.inject.Provider;

public class JpaDAOTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private PersonDAO personDAO;

    @Before
    public final void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("jpa-test");
        em = emf.createEntityManager();
        personDAO = new PersonDAOImpl(new Provider<EntityManager>() {
            public EntityManager get() {
                return em;
            }
        });
        em.getTransaction().begin();
    }

    @After
    public final void tearDown() throws Exception {
        // Always roll back, leaving db "pristine"
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        personDAO = null;
        em.close();
        emf.close();
    }

    private Person newPerson(final String id, final String ssn,
        final String firstName, final String lastName) {
        Person person = new Person();
        person.setId(id);
        person.setSsn(ssn);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        return person;
    }

    @Test
    public final void testInsertRecords() throws Throwable {

        Assert.assertNotNull(emf.getCache());

        String id = UUID.randomUUID().toString();
        Person person = newPerson(id, "111111111", "First", "Last");

        Assert.assertNull(em.find(Person.class, id));
        personDAO.create(person);
        Assert.assertNotNull(em.find(Person.class, id));
    }

    @Test
    public final void testUpdateRecords() throws Throwable {

        String id = UUID.randomUUID().toString();
        Person person = newPerson(id, "111111111", "First", "Last");

        Assert.assertNull(em.find(Person.class, id));
        personDAO.create(person);
        Assert.assertNotNull(em.find(Person.class, id));

        person.setLastName("NewLastName");
        personDAO.update(person);

        person = em.find(Person.class, id);
        Assert.assertNotNull(person);
        Assert.assertEquals("NewLastName", person.getLastName());
    }

    @Test
    public final void testUpdateNonExistentRecord() throws Throwable {
        String id = UUID.randomUUID().toString();
        Person person = newPerson(id, "111111111", "First", "Last");

        person.setLastName("NewLastName");

        person = em.find(Person.class, id);
        Assert.assertNull(person);
    }

    @Test
    public final void testFind() throws Throwable {
        Person person;

        person = newPerson(UUID.randomUUID().toString(), "111111111", "First",
            "Last");
        personDAO.create(person);

        person = newPerson(UUID.randomUUID().toString(), "222222222", "First2",
            "Last2");
        personDAO.create(person);

        List<Person> persons;

        persons = personDAO.namedQuery("Person.byLastName")
            .setParameter("name", "Last2").asList();
        Assert.assertEquals(1, persons.size());

        // persons = personDAO.namedQuery("Person.all").asList();
        // Assert.assertEquals(2, persons.size());

        persons = personDAO.namedQuery("Person.byLastName")
            .setParameter("name", "Last3").asList();
        Assert.assertEquals(0, persons.size());

    }

    private static final int LONG_STRING_LENGTH = 300;

    @Test(expected = DAOException.class)
    public final void testDAOException() throws Throwable {
        StringBuilder longString = new StringBuilder();
        for (int i = 0; i < LONG_STRING_LENGTH; i++) {
            longString.append("a");
        }

        Person person = newPerson(UUID.randomUUID().toString(), "111111111", longString.toString(),
            longString.toString());
        personDAO.create(person);
    }
}
