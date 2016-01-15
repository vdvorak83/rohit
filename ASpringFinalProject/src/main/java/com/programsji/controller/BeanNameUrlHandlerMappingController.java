package com.programsji.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

//@Controller
public class BeanNameUrlHandlerMappingController extends AbstractController {

	// @RequestMapping(value = "/", method = RequestMethod.GET)
	// public String myMessage(ModelMap modelMap) {
	// modelMap.addAttribute("message", "This is just a simple Message");
	// return "message";
	// }

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("message",
				"Bean Name Url Handler Mapping Controller Example");
		mav.setViewName("message");
		return mav;
	}

}
