package com.digitalbook.book.modal;

import org.springframework.http.HttpStatus;

public class ExceptionError {

	int code;
	String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ExceptionError() {
		
	}
	public ExceptionError(String message) {
		super();
		this.message = message;
	}

	public void setCode(HttpStatus badRequest) {
		// TODO Auto-generated method stub
		
	}

}
