package com.fstation.account.service.user;

import com.fstation.account.dto.user.UserDTO;
import com.fstation.account.service.user.exception.UserException;
import com.fstation.account.service.user.impl.UserServiceImpl;
import com.google.inject.ImplementedBy;

@ImplementedBy(UserServiceImpl.class)
public interface UserService {
	UserDTO getAllUsers() throws UserException;
}
