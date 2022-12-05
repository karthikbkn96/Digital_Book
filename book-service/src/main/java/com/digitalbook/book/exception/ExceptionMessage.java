package com.digitalbook.book.exception;

public class ExceptionMessage extends Exception {

	private int errorCode;
	private String errorMessage;

	public ExceptionMessage(Throwable throwable) {
		super(throwable);
	}

	public ExceptionMessage(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public ExceptionMessage(String msg) {
		super(msg);
	}

	public ExceptionMessage(String message, int errorCode) {
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
