package com.programsji.controller;

import com.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.programsji.bo.Person;

@Controller
public class DemoController {

	private static final Logger logger = LoggerFactory
			.getLogger(DemoController.class);

	@RequestMapping(value = { "/demo", "/" }, method = RequestMethod.GET)
	public String demo(Locale locale, Model model) {
		if (model.asMap().get("personlist") != null) {
			logger.debug(model.asMap().get("personlist").toString());
		}
		return "demo";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	private String getlist(RedirectAttributes attr) {
		List<Person> personlist = new ArrayList<Person>();
		personlist.add(new Person(1, "Steve", "Gates"));
		personlist.add(new Person(2, "Bill", "Jobs"));
		attr.addFlashAttribute("personlist", personlist);
		return "redirect:demo";
	}
}
