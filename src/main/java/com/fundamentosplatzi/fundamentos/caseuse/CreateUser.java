package com.fundamentosplatzi.fundamentos.caseuse;

import org.springframework.stereotype.Component;

import com.fundamentosplatzi.fundamentos.entity.User;
import com.fundamentosplatzi.fundamentos.service.UserService;

@Component
public class CreateUser {
	UserService userService;
	
	public CreateUser(UserService userService) {
		this.userService = userService;
	}
	
	public User save(User newUser) {
		return userService.saveUser(newUser);
	}
	
}
