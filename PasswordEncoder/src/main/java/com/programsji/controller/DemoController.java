package com.programsji.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemoController {
	private static final Logger logger = LoggerFactory
			.getLogger(DemoController.class);
	@Autowired
	PasswordEncoder passwordEncoder;

	@RequestMapping(value = "/demo", method = RequestMethod.GET)
	public String demo(Locale locale, Model model) {
		return "demo";
	}

	// You can use below code , when you save users in database and need to
	// encrypt password
	@RequestMapping(value = "/encodeapassword", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		String originalPassword = "john";
		String encodedpassword = passwordEncoder.encode(originalPassword);
		logger.info(encodedpassword);
		return "demo";
	}
}