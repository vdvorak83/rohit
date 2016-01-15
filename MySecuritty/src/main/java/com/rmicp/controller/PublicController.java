package com.rmicp.controller;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rmicp.bo.PersonalPost;
import com.rmicp.bo.PublicPost;
import com.rmicp.service.PersonalService;
import com.rmicp.service.PublicService;

@Controller
@RequestMapping("/public")
public class PublicController {
	@Resource(name = "publicService")
	public PublicService publicService;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String getEditPage(Model model) {
		if (publicService.edit(new PublicPost()) == true) {
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
		if (publicService.add(new PublicPost()) == true) {
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
		if (publicService.delete(new PublicPost()) == true) {
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
