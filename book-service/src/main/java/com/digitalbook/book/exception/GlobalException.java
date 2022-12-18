package com.digitalbook.book.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.digitalbook.book.modal.ExceptionError;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exception(ExceptionError ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());

	}

	@ExceptionHandler(RunTimeExceptionMessage.class)
	public ResponseEntity<?> exception(RunTimeExceptionMessage ex) {
		return ResponseEntity.badRequest().body(ex.getErrorMessage());

	}

	@ExceptionHandler(MissingServletRequestPartException.class)
	public ResponseEntity<?> handleMissingServletRequestPartException(MissingServletRequestPartException exception,
			HttpServletResponse response) {
		return ResponseEntity.badRequest().body(exception.getMessage());
	}

}
