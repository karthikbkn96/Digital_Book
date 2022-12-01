package com.user.exception;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class globalException {

    @ExceptionHandler(customCheckedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Object processValidationError(customCheckedException exception) {
        String result = exception.getMessage();
        System.out.println("###########"+result);
        JSONObject obj = new JSONObject();
        obj.append("Error Message", exception.getErrorMessage());
        obj.append("Error Code", exception.getErrorCode());
        return obj.toString();
    }
}