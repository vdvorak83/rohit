package com.programsji.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.programsji.annotation.CustomAnnotationForName;

@Controller
public class DemoController {
	private static final Logger logger = LoggerFactory
			.getLogger(DemoController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String demo() {
		return "demo";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String demo(@CustomAnnotationForName String name, Model model) {
		logger.debug("Submitted name's value is " + name);
		model.addAttribute("name", name);
		return "demo";
	}
}