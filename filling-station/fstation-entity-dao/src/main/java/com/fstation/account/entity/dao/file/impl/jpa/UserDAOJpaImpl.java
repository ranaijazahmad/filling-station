package com.fstation.account.entity.dao.file.impl.jpa;

import javax.persistence.EntityManager;

import com.fstation.account.entity.dao.file.UserDAO;
import com.fstation.account.entity.dao.file.model.User;
import com.fstation.core.dao.jpa.AbstractJpaDAO;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class UserDAOJpaImpl extends AbstractJpaDAO<User, String> implements
		UserDAO {
	@Inject
	UserDAOJpaImpl(final Provider<EntityManager> emProvider) {
		super(User.class, emProvider);
	}
}
