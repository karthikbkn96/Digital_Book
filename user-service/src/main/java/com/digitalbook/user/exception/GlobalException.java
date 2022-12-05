package com.digitalbook.user.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.digitalbook.user.modal.ExceptionError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(Exception.class)
	public JSONObject exception(ExceptionError ex) {
		JSONObject obj = new JSONObject();
		obj.append("Error Message", ex.getMessage());
		obj.append("Error Code", ex.getCode());

		return obj;

	}

	@ExceptionHandler(value =AccessDeniedException.class)
	public JSONObject commence(AccessDeniedException ae, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AccessDeniedException accessDeniedException) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("Error Code", HttpStatus.FORBIDDEN.value());
		obj.put("Error Message", "Access denied for this end point");
		return obj;
	}

}
