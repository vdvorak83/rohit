package com.programsji.controller;

import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoController {

	@RequestMapping(value = "/demo", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "demo";
	}

	@RequestMapping(value = "/usernotloggedin", method = RequestMethod.GET)
	public String usernotloggedin(Locale locale, Model model,
			HttpSession session) {
		model.addAttribute(
				"message",
				"You need to login to access this url"
						+ ((DefaultSavedRequest) session
								.getAttribute("SPRING_SECURITY_SAVED_REQUEST"))
								.getRequestURL());
		return "message";
	}

}
