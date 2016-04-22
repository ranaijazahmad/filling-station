package com.fstation.account.entity.dao.file.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.GenericGenerator;

@NamedQueries({ @NamedQuery(name = "Users.all", query = "SELECT t FROM Users t where t.active=true order by t.name") })
@Entity(name = "Users")
public class User implements Serializable {

	private static final long serialVersionUID = 2346437395122068592L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String userId;

	public final String getUserId() {
		return userId;
	}

	public final void setUserId(final String userId) {
		this.userId = userId;
	}

	@Column(length = 100, name = "name")
	private String name;

	public final String getName() {
		return name;
	}

	public final void setName(final String name) {
		this.name = name;
	}

	@Column(name = "active")
	private Boolean active = true;

	public final Boolean getActive() {
		return active;
	}

	public final void setActive(final Boolean active) {
		this.active = active;
	}

	@Column(name = "username")
	private String userName;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	@Column(name = "password")
	private String password;

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
}
