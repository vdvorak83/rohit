package com.rmicp.controller;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rmicp.bo.AdminPost;
import com.rmicp.bo.PersonalPost;
import com.rmicp.service.AdminService;
import com.rmicp.service.PersonalService;

@Controller
@RequestMapping("/personal")
public class PersonalController {
	@Resource(name = "personalService")
	public PersonalService personalService;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String getEditPage(Model model) {
		if (personalService.edit(new PersonalPost()) == true) {
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
		if (personalService.add(new PersonalPost()) == true) {
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
		if (personalService.delete(new PersonalPost()) == true) {
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
