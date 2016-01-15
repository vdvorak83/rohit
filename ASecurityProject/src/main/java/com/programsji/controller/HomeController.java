package com.programsji.controller;

import java.util.Date;
import java.util.Locale;

import javax.xml.ws.ResponseWrapper;

import org.apache.http.HttpStatus;
import org.apache.mina.filter.reqres.Request;
import org.codehaus.jettison.json.JSONWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.programsji.bo.Person;
import com.programsji.security.User;

@Controller
public class HomeController {

	@Value("${name}")
	String propertyvalue1;

	// @Secured(value = "ROLE_USER")
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model, Locale locale) {
		model.addAttribute("message", "This is a Simple Message");
		model.addAttribute("message1", propertyvalue1);
		Person p = new Person("Rohit", 10, 1000.43f, new Date());
		model.addAttribute("myperson", p);
		return "home";
	}

	@RequestMapping(value = "/wow", method = RequestMethod.GET)
	public String wow(Model model, Locale locale) {
		model.addAttribute("message", "This is a Simple Message From Wow");
		model.addAttribute("message1", propertyvalue1);
		Person p = new Person("Wow Rohit", 10, 1000.43f, new Date());
		model.addAttribute("myperson", p);
		return "home";
	}

	// @Secured(value = "ROLE_ADMIN")
	@RequestMapping(value = "/home", method = RequestMethod.POST, produces = { "application/xml" })
	public String homePost(@ModelAttribute("myperson") Person person,
			BindingResult br) {
		if (br.hasErrors()) {
			System.out.println("BR has Errors");
		} else {
			System.out.println("BR has No Error");
		}
		return "home";
	}

	// @RequestMapping(value = "/home", method = RequestMethod.GET, produces = {
	// "application/xml", "application/json" })
	// @ResponseStatus(org.springframework.http.HttpStatus.OK)
	// public @ResponseBody
	// Person getJsonResponse(Model model, Locale locale) {
	// return new Person("Rohit", 10, 1000.43f, new Date());
	// }

	@RequestMapping(value = "/employee/home", method = RequestMethod.GET)
	public String home_1(Model model, Locale locale) {
		model.addAttribute("message", "This is a Simple Message");
		model.addAttribute("message1", propertyvalue1);
		Person p = new Person("Rohit", 10, 1000.43f, new Date());
		model.addAttribute("myperson", p);
		return "home";
	}

	@RequestMapping(value = "/customer/home", method = RequestMethod.GET)
	public String home_2(Model model, Locale locale) {
		model.addAttribute("message", "This is a Simple Message");
		model.addAttribute("message1", propertyvalue1);
		Person p = new Person("Rohit", 10, 1000.43f, new Date());
		model.addAttribute("myperson", p);
		return "home";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
	public String home_3(User user) {
		return "home";
	}
}
