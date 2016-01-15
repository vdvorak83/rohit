package com.programsji.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.programsji.bo.Person;

@Controller
@SessionAttributes(value = "person")
public class DemoController {

	private static final Logger logger = LoggerFactory
			.getLogger(DemoController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String demo(Model model) {

		Person p = new Person();
		p.setFname("MyFirstName");// Fixed First Name
		p.setId(1);// Fixed ID
		p.setLname(null);
		model.addAttribute("person", p);
		return "demo";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String submitform(@ModelAttribute("person") Person person,
			Model model) {
		logger.info("id : " + person.getId());
		logger.info("First Name : " + person.getFname());
		logger.info("Last Name : " + person.getLname());
		model.addAttribute("person", person);
		return "demo";
	}

}
