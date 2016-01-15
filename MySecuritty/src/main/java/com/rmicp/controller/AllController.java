package com.rmicp.controller;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rmicp.service.AdminService;
import com.rmicp.service.PersonalService;
import com.rmicp.service.PublicService;

@Controller
@RequestMapping("/all")
public class AllController {
	@Resource(name = "adminService")
	private AdminService adminService;

	@Resource(name = "personalService")
	private PersonalService personalService;

	@Resource(name = "publicService")
	private PublicService publicService;

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String getViewAllPage(Model model) {

		model.addAttribute("adminposts", adminService.getAll());
		model.addAttribute("personalposts", personalService.getAll());
		model.addAttribute("publicposts", publicService.getAll());

		model.addAttribute("role", SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities());
		model.addAttribute("username", SecurityContextHolder.getContext()
				.getAuthentication().getName());

		return "resultpage";
	}
}
