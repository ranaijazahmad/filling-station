package com.fstation.account.service.user.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fstation.account.dto.user.UserDTO;
import com.fstation.account.entity.dao.file.UserDAO;
import com.fstation.account.entity.dao.file.model.User;
import com.fstation.account.service.user.UserService;
import com.fstation.account.service.user.exception.UserException;
import com.fstation.core.dao.exception.DAOException;
import com.fstation.core.dao.jpa.EntityManagerProvider;
import com.google.inject.Inject;

public class UserServiceImpl implements UserService {
	private UserDAO userDAO;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserServiceImpl.class);
	private EntityManagerProvider entityManagerProvider;

	@Inject
	public UserServiceImpl(final UserDAO userDAO,
			final EntityManagerProvider entityManagerProvider) {
		this.userDAO = userDAO;
		this.entityManagerProvider = entityManagerProvider;
	}

	@Override
	public UserDTO getAllUsers() throws UserException {
		try {
			List<User> users = userDAO.namedQuery("Users.all").asList();
			LOGGER.info("" + users.size());
		} catch (DAOException e) {
			throw new UserException("exception in all users!", e);
		}
		return null;
	}
}
