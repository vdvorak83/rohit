package com.programsji.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.programsji.bo.Person;
import com.programsji.bo.ResponseWrapper;

@Controller
public class DemoController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String demo(Locale locale, Model model) {
		return "demo";
	}

	@RequestMapping(value = "/submitform", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseWrapper home(@RequestBody Person person) {
		if (person == null) {
			return new ResponseWrapper("failure", "person is null",
					"person object cannot be null");
		} else {

			return new ResponseWrapper("success", "person has Values",
					"Values in person object are :" + person);
		}
	}

	
}