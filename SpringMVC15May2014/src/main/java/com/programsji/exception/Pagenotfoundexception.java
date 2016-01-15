package com.programsji.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Component
@ControllerAdvice
public class Pagenotfoundexception {
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(Exception.class)
	public ModelAndView urlNotFoundException() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "An Error Occured in your application");
		mv.setViewName("message");
		return mv;
	}
}
