package com.fstation.account.web.guice;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.servlet.GuiceServletContextListener;

public abstract class AbstractGuiceServletConfig extends
		GuiceServletContextListener {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(AbstractGuiceServletConfig.class);

	private ServletContext servletContext;

	@Override
	public final void contextInitialized(
			final ServletContextEvent servletContextEvent) {
		this.servletContext = servletContextEvent.getServletContext();

		super.contextInitialized(servletContextEvent);
	}

	public final ServletContext getServletContext() {
		return servletContext;
	}

	public final void setServletContext(final ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
