package com.digitalbook.book.exception;

public class RunTimeExceptionMessage extends RuntimeException{

	private int errorCode;
	private String errorMessage;

	public RunTimeExceptionMessage(Throwable throwable) {
		super(throwable);
	}

	public RunTimeExceptionMessage(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public RunTimeExceptionMessage(String msg) {
		super(msg);
	}

	public RunTimeExceptionMessage(String message, int errorCode) {
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
