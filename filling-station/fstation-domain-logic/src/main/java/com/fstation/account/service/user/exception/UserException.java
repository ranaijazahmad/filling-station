package com.fstation.account.service.user.exception;

public class UserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6975938608475601918L;

	public UserException() {

	}

	public UserException(final String arg0) {
		super(arg0);

	}

	public UserException(final Throwable arg0) {
		super(arg0);

	}

	public UserException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);

	}
}
