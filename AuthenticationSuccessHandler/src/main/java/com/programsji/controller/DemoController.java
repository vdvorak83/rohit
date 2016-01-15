package com.programsji.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoController {

	@RequestMapping(value = "/userdemo", method = RequestMethod.GET)
	public String userdemo(Locale locale, Model model) {
		return "userdemo";
	}
	
	@RequestMapping(value = "/admindemo", method = RequestMethod.GET)
	public String admindemo(Locale locale, Model model) {
		return "admindemo";
	}
}