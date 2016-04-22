package com.fstation.core.dao.jpa;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

/**
 * @author matt.nettleton
 */
public class EntityManagerCloser {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityManagerCloser.class);

    private final EntityManagerProvider emProvider;

    @Inject
    public EntityManagerCloser(final EntityManagerProvider emProvider) {
        this.emProvider = emProvider;
    }

    public final void cleanup() {
        EntityManager em = emProvider.get(false);
        try {
            if (em != null && em.isOpen()) {
                if (em.getTransaction().isActive()) {
                    LOGGER.warn("Transaction not committed, rolling back");
                    em.getTransaction().rollback();
                }
                em.close();
            }
        } catch (Exception e) {
            LOGGER.error("Failed to close Entity Manager: " + e.getMessage(), e);
        }
    }

}
