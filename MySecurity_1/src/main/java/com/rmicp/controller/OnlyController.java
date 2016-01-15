package com.rmicp.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OnlyController {

	private static final Logger logger = LoggerFactory
			.getLogger(OnlyController.class);

	@RequestMapping(value = "/visitor", method = RequestMethod.GET)
	public String homeVisitor(Model model) {
		model.addAttribute("message", "We Are In  Visitor Page");
		return "home";
	}

	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public String homeCustomer(Model model) {
		model.addAttribute("message", "We Are In Customer Page");
		return "home";
	}

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public String homeEmployee(Model model) {
		model.addAttribute("message", "We Are In Employee Page");
		return "home";
	}

	@RequestMapping(value = "/ajaxrequest", method = RequestMethod.GET)
	@ResponseBody
	public String ajaxRequest(
			@RequestParam(value = "name", required = false) String name) {
		return "Hello Mr. " + name;
	}

	@RequestMapping(value = "/http403forbidden", method = RequestMethod.GET)
	public String http403Forbidden(Model model) {
		model.addAttribute("message", "Error http403Forbidden Page");
		return "home";
	}

	@RequestMapping(value = "/logoutpage", method = RequestMethod.GET)
	public String logoutPage(Model model) {
		model.addAttribute("message",
				"You have been logged out from this page.");
		return "home";
	}

	@RequestMapping(value = "/withoutcredentials", method = RequestMethod.GET)
	public String withoutcredentials(Model model) {
		model.addAttribute("message",
				"Sorry , You have to enter credentials to proceed");
		return "home";
	}

	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String accessdenied(Model model) {
		model.addAttribute("message", "Sorry, You Dont Have This Page's Access");
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

}
