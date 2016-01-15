package com.programsji.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.programsji.config.CustomSessionListener;

@Controller
public class DemoController {

	private static final Logger logger = LoggerFactory
			.getLogger(DemoController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String demo(Model model, HttpServletRequest request) {
		request.getSession(true);
		model.addAttribute("noofsession", CustomSessionListener.activeSessions);
		return "demo";
	}

	@RequestMapping(value = "/destroysession", method = RequestMethod.GET)
	public String destroysession(Model model, HttpServletRequest request) {
		if (request.getSession(false) != null) {
			request.getSession(false).invalidate();
		}
		model.addAttribute("noofsession", CustomSessionListener.activeSessions);
		return "demo";
	}

}
