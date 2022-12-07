package com.digitalbook.user.exception;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.digitalbook.user.modal.ExceptionError;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public JSONObject exception(ExceptionError ex) {
		JSONObject obj = new JSONObject();
		obj.append("Error Message", ex.getMessage());
		obj.append("Error Code", ex.getCode());

		return obj;

	}
	
	@ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(Exception ex, WebRequest request) {
		JSONObject obj = new JSONObject();
		obj.put("Error Code", "401");
		obj.put("Error Message", "Access denied for this end point");
		return obj.toString();
    }
}
