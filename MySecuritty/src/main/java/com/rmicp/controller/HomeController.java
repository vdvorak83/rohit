package com.rmicp.controller;

import java.io.Reader;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("message", "I came from home");
		System.out.println("I am Here, You Are Where????");
		// ConcurrentSessionFilter
		return "home";
	}

	@RequestMapping(value = "/home1", method = RequestMethod.GET)
	public String home1(Model model) {
		model.addAttribute("message", "I am in HOME1 ");
		System.out.println("I am In Home1");
		// ConcurrentSessionFilter
		return "home";
	}

	@RequestMapping(value = "/customer/home", method = RequestMethod.GET)
	public String customerHome(Model model) {
		model.addAttribute("message", "I came from Customer home");
		return "home";
	}

	@RequestMapping(value = "/employee/home", method = RequestMethod.GET)
	public String employeeHome(Model model) {
		model.addAttribute("message", "I came from Employee home");
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/forbiddenentrypoint", method = RequestMethod.GET)
	public String forbiddenEntryPoint(Model model) {
		return "http403forbiddenentrypoint";
	}

	@RequestMapping("/getajaxrequest")
	@Secured("ROLE_USER")
	@ResponseBody
	public String getAjaxRequest(
			@RequestParam(value = "name", required = false) String name) {
		return "Hello Mr." + name;
	}

}
