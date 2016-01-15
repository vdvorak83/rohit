package com.programsji.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String demo(Model model) {
		model.addAttribute("message", "A Message");
		return "demo";
	}

}
