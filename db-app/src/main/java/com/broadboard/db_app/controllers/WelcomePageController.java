package com.broadboard.db_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.broadboard.db_app.models.User;
import com.broadboard.db_app.services.UserService;

@Controller
@RequestMapping("/userview")
public class WelcomePageController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/list")
	public ModelAndView viewUsers() {
		List<User> users = userService.getAllUsers();
		ModelAndView modelAndView = new ModelAndView("welcome.html");//name of Thymeleaf template
		modelAndView.addObject("users", users);
		return modelAndView;
	}
	
	// Endpoint to view user details
    @GetMapping("/details/{id}")
    public ModelAndView viewUserDetails(@PathVariable Long id) {
        User user = userService.getUserById(id);
        ModelAndView modelAndView = new ModelAndView("user-details"); // Thymeleaf template name
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    // Endpoint to add a new user
    @GetMapping("/add")
    public ModelAndView addUserForm() {
        ModelAndView modelAndView = new ModelAndView("user-form"); // Thymeleaf template name
        modelAndView.addObject("user", new User()); // Create an empty User object
        return modelAndView;
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/users/list"; // Redirect to the user list view
    }

    // Endpoint to edit an existing user
    @GetMapping("/edit/{id}")
    public ModelAndView editUserForm(@PathVariable Long id) {
        User user = userService.getUserById(id);
        ModelAndView modelAndView = new ModelAndView("user-form"); // Thymeleaf template name
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute User user) {
        userService.updateUser(user.getId(), user);
        return "redirect:/users/list"; // Redirect to the user list view
    }

    // Endpoint to delete a user
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users/list"; // Redirect to the user list view
    }
}
