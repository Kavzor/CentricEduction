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

	@RequestMapping("/home")
	public ModelAndView handleHome(@RequestParam("name") String name, HttpServletRequest request, ModelAndView model) {
		model.setViewName("index");
		model.addObject("date", new Date());
		model.addObject("reversedName", new StringBuilder(name).reverse());
		model.addObject("httpMethod", request.getMethod());
		
		return model;
	}
}
