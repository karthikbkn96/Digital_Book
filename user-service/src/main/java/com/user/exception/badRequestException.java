package com.user.exception;

public class badRequestException extends Exception {

	private static final long serialVersionUID = 1L;

	public badRequestException() {
		super();
	}

	public badRequestException(String message) {
		super(message);
	}

	public badRequestException(String message, Throwable t) {
		super(message, t);
	}

	public badRequestException(Throwable t) {
		super(t);
	}

}
