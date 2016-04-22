package com.fstation.account.entity.dao.file;

import com.fstation.account.entity.dao.file.impl.jpa.UserDAOJpaImpl;
import com.fstation.account.entity.dao.file.model.User;
import com.fstation.core.dao.DAO;
import com.google.inject.ImplementedBy;

@ImplementedBy(UserDAOJpaImpl.class)
public interface UserDAO extends DAO<User, String> {

}
