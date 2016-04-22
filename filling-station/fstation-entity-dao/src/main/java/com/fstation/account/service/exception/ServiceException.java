package com.fstation.account.service.exception;

import com.fstation.account.entity.dao.user.model.SiteUser;

/**
 * @author m.ijaz
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 4160165496260614618L;
	private SiteUser user;
	private String fileName;
	private String fileID;
	private String description;
	private Exception exception;

	public final SiteUser getUser() {
		return user;
	}

	public final void setUser(final SiteUser user) {
		this.user = user;
	}

	public final Exception getException() {
		return exception;
	}

	public final void setException(final Exception exception) {
		this.exception = exception;
	}

	public final String getFileName() {
		return fileName;
	}

	public final void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	public final String getFileID() {
		return fileID;
	}

	public final void setFileID(final String fileID) {
		this.fileID = fileID;
	}

	public final String getDescription() {
		return description;
	}

	public final void setDescription(final String description) {
		this.description = description;
	}

}
