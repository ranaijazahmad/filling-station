package com.fstation.account.web.guice;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fstation.account.service.user.UserService;
import com.google.inject.Injector;

/**
 * @author m.ijaz
 */
@ManagedBean(name = "services", eager = true)
@ApplicationScoped
public final class ServiceBean {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServiceBean.class);

	private UserService userService;

	@PostConstruct
	public void startup() {
		ServletContext context = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();
		Injector injector = (Injector) context.getAttribute(Injector.class
				.getName());
		userService = injector.getInstance(UserService.class);

	}

	@PreDestroy
	public void shutdown() {
	}

	public UserService getUserService() {
		return userService;
	}

}
