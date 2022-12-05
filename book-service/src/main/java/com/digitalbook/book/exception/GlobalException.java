package com.digitalbook.book.exception;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.digitalbook.book.modal.ExceptionError;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(Exception.class)
	public JSONObject exception(ExceptionError ex) {
		JSONObject obj = new JSONObject();
		obj.append("Error Message", ex.getMessage());
		obj.append("Error Code", ex.getCode());

		return obj;

	}
	
	@ExceptionHandler(RunTimeExceptionMessage.class)
	public JSONObject exception(RunTimeExceptionMessage ex) {
		JSONObject obj = new JSONObject();
		obj.append("Error Message", ex.getErrorMessage());
		obj.append("Error Code", ex.getErrorCode());

		return obj;

	}

	@ExceptionHandler(MissingServletRequestPartException.class)
	public @ResponseBody JSONObject handleMissingServletRequestPartException(MissingServletRequestPartException exception,
			HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		obj.append("missing parameters", exception.getMessage());
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return obj;
	}

}
