package com.fstation.account.web.guice.modules;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fstation.core.dao.jpa.EntityManagerProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * @author m.ijaz
 */
public class EntityManagerProviderModule extends AbstractModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityManagerProviderModule.class);

    @Override
    protected final void configure() {
        bind(EntityManager.class).toProvider(EntityManagerProvider.class);
    }

    @Provides
    @Singleton
    final EntityManagerFactory providesEntityManagerFactory() {
        try {
            return Persistence.createEntityManagerFactory("fstation-entity-dao");
        } catch (RuntimeException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

}
