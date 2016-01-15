package com.programsji.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.ui1.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.programsji.bo.ResponseWrapper;

@Component
@ControllerAdvice
public class MyException {
	@ResponseBody
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public ResponseWrapper handleArgumentNotValidException(
			HttpMessageNotReadableException ex) {
		return new ResponseWrapper("failure",
				"HandleArgumentNotValidException", ex.getMessage());
	}
}