package com.fstation.core.dao.jpa.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
    @NamedQuery(name = "Person.all", query = "SELECT p FROM person p"),
    @NamedQuery(name = "Person.byLastName", query = "SELECT p FROM person p WHERE p.lastName = :name")
})
@Entity(name = "person")
public class Person implements Serializable {

    private static final long serialVersionUID = 5218868317969727145L;

    @Id
    private String id;
    private String ssn;
    private String firstName;
    private String lastName;

    public final String getId() {
        return id;
    }

    public final void setId(final String id) {
        this.id = id;
    }

    public final String getSsn() {
        return ssn;
    }

    public final void setSsn(final String ssn) {
        this.ssn = ssn;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public final String getLastName() {
        return lastName;
    }

    public final void setLastName(final String lastName) {
        this.lastName = lastName;
    }
}
