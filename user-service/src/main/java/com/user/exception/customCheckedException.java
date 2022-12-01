package com.user.exception;

public class customCheckedException extends Exception {
	private int errorCode;
	private String errorMessage;

	public customCheckedException(Throwable throwable) {
		super(throwable);
	}

	public customCheckedException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public customCheckedException(String msg) {
		super(msg);
	}

	public customCheckedException(String message, int errorCode) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = message;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public String toString() {
		return this.errorCode + " : " + this.getErrorMessage();
	}
}
