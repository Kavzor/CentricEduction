package com.centric.spring.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class User {
	
	private Long id;
	
	private int loginCounter;
	
	private String username;
	private String password;
	
	
	public Long getId() {
		return id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public void setId(Long id) {
		this.id = id;
	}
	public int getLoginCounter() {
		return loginCounter;
	}
	public void setLoginCounter(int loginCounter) {
		this.loginCounter = loginCounter;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
