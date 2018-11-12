package com.centric.spring.service;

import java.util.List;

import com.centric.spring.repository.User;

public interface UserService {
	void save(User user);
	void update(User user);
	User find(String username);
	List<User> findAll();
}
