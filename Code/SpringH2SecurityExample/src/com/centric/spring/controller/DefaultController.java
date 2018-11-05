package com.centric.spring.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.centric.spring.repository.User;
import com.centric.spring.service.UserService;

@RequestMapping("/")
@Controller
public class DefaultController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(path = {"/", "/home"})
	public String handleDefault() {
		return "index";
	}
	
	@RequestMapping(path = "/profile")
	public ModelAndView handleProfile(Principal principal) {
		User user = userService.find(principal.getName());
		user.setLoginCounter(user.getLoginCounter() + 1);
		
		userService.update(user);
		
		ModelAndView model = new ModelAndView("profile");
		model.addObject("user", user);
		
		return model;
	}
	
	@RequestMapping(path = "/register")
	public String handleRegister() {
		return "register_form";
	}
	
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public String registration(@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("residence") String residence ) {
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setResidence(residence);
		
		userService.save(user);
		
		return "redirect:/login";
	}
}
