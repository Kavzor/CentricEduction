package com.centric.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centric.spring.repository.User;
import com.centric.spring.service.UserService;

@RestController
@RequestMapping("api")
public class BuddiesController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/buddies")
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.ok(userService.findAll());
	}
	
	//h�mta en specifik anv�ndare
	@RequestMapping("/buddies/{username}")
	public ResponseEntity<User> find(@PathVariable("username") String username) {
		User user = userService.find(username);
		if(user == null) {
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.ok(user);
		}
	}
}
