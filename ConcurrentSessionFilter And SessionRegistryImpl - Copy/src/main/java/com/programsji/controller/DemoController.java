package com.programsji.controller;

import java.util.Locale;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.programsji.security.CustomSessionRegistryImpl;

@Controller
public class DemoController {

	private static final Logger logger = LoggerFactory
			.getLogger(DemoController.class);

	@Resource(name = "sessionRegistry")
	// @Autowired
	SessionRegistry sessionRegistry;

	@RequestMapping(value = "/userpage", method = RequestMethod.GET)
	public String userpage(Locale locale, Model model) {
		return "userpage";
	}

	@RequestMapping(value = "/adminpage", method = RequestMethod.GET)
	public String adminpage(Locale locale, Model model) {
		int loggedinusers = sessionRegistry.getAllPrincipals().size();
		logger.info("Logged in Users : " + loggedinusers);
		logger.info("Users info :");
		for (Object user : sessionRegistry.getAllPrincipals()) {
			logger.info(((User) user).getUsername());
		}

		logger.info("Total Users including expires :"
				+ sessionRegistry.getAllSessions(
						sessionRegistry.getAllPrincipals().get(0), true).size());

		logger.info("Total Sessions :"
				+ sessionRegistry.getAllSessions(
						sessionRegistry.getAllPrincipals().get(0), false)
						.size());

		model.addAttribute("users", sessionRegistry.getAllPrincipals());
		return "adminpage";
	}
}
