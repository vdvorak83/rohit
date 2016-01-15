package com.programsji.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoController {

	private static final Logger logger = LoggerFactory
			.getLogger(DemoController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String demo(SitePreference sitePreference, Device device, Model model) {
		logger.info(sitePreference.name());
		logger.info(device.toString());
		model.addAttribute("sitepreference", sitePreference.name());
		model.addAttribute("device", device);
		return "demo";
	}

}
