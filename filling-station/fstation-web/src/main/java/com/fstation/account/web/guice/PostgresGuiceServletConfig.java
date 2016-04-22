package com.fstation.account.web.guice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fstation.account.web.guice.modules.AiredaleServletModule;
import com.fstation.account.web.guice.modules.EntityManagerProviderModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class PostgresGuiceServletConfig extends AbstractGuiceServletConfig {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PostgresGuiceServletConfig.class);

	@Override
	protected final Injector getInjector() {
		try {
			Injector injector = Guice.createInjector(
					new AiredaleServletModule(),
					new EntityManagerProviderModule());

			return injector;
		} catch (Exception e) {
			LOGGER.error("Error looking up server resources for file archive!",
					e);
			return null;
		}

	}

	// CHECKSTYLE:ON

}
