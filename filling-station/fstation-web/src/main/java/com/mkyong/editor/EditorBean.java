package com.mkyong.editor;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.fstation.account.service.user.UserService;
import com.fstation.account.service.user.exception.UserException;

@ManagedBean(name = "editor")
public class EditorBean {
	@ManagedProperty("#{services.userService}")
	private UserService userService;
	private String value = "This editor is provided by PrimeFaces";

	@PostConstruct
	public void init() {
		try {
			userService.getAllUsers();
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return userService;
	}
}