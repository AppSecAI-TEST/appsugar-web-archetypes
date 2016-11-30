package org.appsugar.controller.domain;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseEntityDto implements Serializable {

	private static final long serialVersionUID = -3147648514354109731L;

	protected Long id;
	protected Date createdAt;
	protected Date updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
