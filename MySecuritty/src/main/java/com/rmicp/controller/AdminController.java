package com.rmicp.controller;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rmicp.bo.AdminPost;
import com.rmicp.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Resource(name = "adminService")
	public AdminService adminService;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String getEditPage(Model model) {
		if (adminService.edit(new AdminPost()) == true) {
			model.addAttribute("result", "Entry has been edited successfully!");
		} else {
			model.addAttribute("result",
					"You're not allowed to perform that action!");
		}
		model.addAttribute("source", "Admin >> Edit");
		model.addAttribute("role", SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities());
		model.addAttribute("username", SecurityContextHolder.getContext()
				.getAuthentication().getName());
		return "resultpage";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddPage(Model model) {
		if (adminService.add(new AdminPost()) == true) {
			model.addAttribute("result", "Entry has been added successfully!");
		} else {
			model.addAttribute("result",
					"You're not allowed to perform that action!");
		}

		model.addAttribute("source", "Admin >> Add");
		model.addAttribute("role", SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities());
		model.addAttribute("username", SecurityContextHolder.getContext()
				.getAuthentication().getName());
		return "resultpage";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String getDeletePage(Model model) {
		if (adminService.delete(new AdminPost()) == true) {
			model.addAttribute("result", "Entry has been deleted successfully!");
		} else {
			model.addAttribute("result",
					"You're not allowed to perform that action!");
		}

		model.addAttribute("source", "Admin >> Delete");

		model.addAttribute("role", SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities());
		model.addAttribute("username", SecurityContextHolder.getContext()
				.getAuthentication().getName());

		return "resultpage";
	}
}
