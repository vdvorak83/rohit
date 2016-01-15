package com.programsji.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@Component
@ControllerAdvice
public class CustomNoSuchRequestHandingMethodException {
	// @ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchRequestHandlingMethodException.class)
	public ModelAndView urlNotFoundException() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", "No Such Request Handling Method Exception");
		mav.setViewName("message");
		return mav;
	}
}
