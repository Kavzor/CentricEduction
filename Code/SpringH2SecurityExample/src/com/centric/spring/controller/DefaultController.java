package com.centric.spring.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/")
@Controller
public class DefaultController {
	
	@RequestMapping(path = {"/", "/home"})
	public String handleDefault() {
		return "index";
	}
	
	@RequestMapping("/profile")
	public String handleProfile() {
		return "profile";
	}
}
