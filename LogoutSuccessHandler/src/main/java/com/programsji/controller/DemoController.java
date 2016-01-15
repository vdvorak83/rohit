package com.programsji.controller;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoController {
	private static final Logger logger = LoggerFactory
			.getLogger(DemoController.class);

	@RequestMapping(value = "/demo", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletResponse response) {

		Cookie cookie = new Cookie("firstrequesttime", new Date().toString());
		response.addCookie(cookie);
		return "demo";
	}

}
