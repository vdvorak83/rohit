package com.programsji.controller;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.ResponseWrapper;

import org.apache.http.HttpStatus;
import org.apache.mina.filter.reqres.Request;
import org.codehaus.jettison.json.JSONWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.programsji.bo.Person;

@Controller
@RequestMapping(value = "my.htm")
public class MyController {

	@Value("${name}")
	String propertyvalue1;

	@RequestMapping(method = RequestMethod.GET)
	public String home1(Model model, Locale locale) {
		model.addAttribute("message", "This is a Simple Message From My Home");
		model.addAttribute("message1", propertyvalue1);
		return "home";
	}

	// @RequestMapping(value = "/home", method = RequestMethod.GET, produces = {
	// "application/xml", "application/json" })
	// @ResponseStatus(org.springframework.http.HttpStatus.OK)
	// public @ResponseBody
	// Person getJsonResponse(Model model, Locale locale) {
	// return new Person("Rohit", 10, 1000.43f, new Date());
	// }
}
