package com.programsji.controller;

import java.util.Locale;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoController {
	private static final Logger logger = LoggerFactory
			.getLogger(DemoController.class);

	@RequestMapping(value = "/demo", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		Object password = SecurityContextHolder.getContext()
				.getAuthentication().getCredentials();

		UserDetails user = (UserDetails) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		Set<String> authorities = AuthorityUtils.authorityListToSet(auth
				.getAuthorities());
		logger.info("Username is  : " + user.getUsername());
		logger.info("Password is  : " + password);
		logger.info("Granted Authorities Are : " + authorities);
		return "demo";
	}
}