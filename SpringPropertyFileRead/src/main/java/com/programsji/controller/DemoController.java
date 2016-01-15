package com.programsji.controller;


import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoController {

	@Autowired
	Environment env;

	@Value("${db.url}")
	String url;
	@Value("${db.username}")
	String username;

	private static final Logger logger = LoggerFactory
			.getLogger(DemoController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Going to Read Properties From classpath:property1.properties,peoperty2.properties files");
		logger.info("USING ENVIRONMENT");
		logger.info(env.getProperty("db.url"));
		logger.info(env.getProperty("db.username"));

		logger.info("USING @Value");
		logger.info(url);
		logger.info(username);

		model.addAttribute("url", url);
		model.addAttribute("username", username);
		return "demo";
	}
}