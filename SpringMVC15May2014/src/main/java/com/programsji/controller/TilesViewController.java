package com.programsji.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.site.SitePreference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TilesViewController {
	@RequestMapping("/mytilesview")
	public String mytilesview(SitePreference sitePreference, Device device,
			Model model, HttpServletRequest request) {

		System.out.println("SitePreference : " + sitePreference);
		System.out.println("Device : " + device);
		request.setAttribute("message", "I am Coming From Welcome Controller");
		return "mytilesview";
	}
}