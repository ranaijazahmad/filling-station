package com.fstation.account.entity.dao.base.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractActivatableEntity extends AbstractAiredaleEntity {

	private boolean active;

	public boolean getActive() {
		return active;
	}

	public void setActive(final boolean active) {
		this.active = active;
	}
}
