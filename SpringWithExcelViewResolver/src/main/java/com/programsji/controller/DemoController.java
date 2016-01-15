package com.programsji.controller;

import com.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.programsji.bo.Person;

@Controller
public class DemoController {

	private static final Logger logger = LoggerFactory
			.getLogger(DemoController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String demo(Model model) {
		return "demo";
	}

	@RequestMapping(value = "/exportexcel", method = RequestMethod.GET)
	public String exportexcel(Model model) {
		List<Person> personList = new ArrayList<Person>();
		personList.add(new Person(1, "FirstName1", "LastName1"));
		personList.add(new Person(1, "FirstName2", "LastName2"));
		personList.add(new Person(1, "FirstName3", "LastName3"));
		personList.add(new Person(1, "FirstName4", "LastName4"));
		model.addAttribute("personlist", personList);
		return "personlistExcelformat";
	}

}
