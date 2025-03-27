package com.zinnov.learnmorr_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.zinnov.learnmorr_app.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	// display a list of users
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listUsers", userService.getAllUsers());
		return "index";
	}

}
