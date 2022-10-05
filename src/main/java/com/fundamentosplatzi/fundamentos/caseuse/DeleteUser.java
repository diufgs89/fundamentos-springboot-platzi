package com.fundamentosplatzi.fundamentos.caseuse;

import org.springframework.stereotype.Component;

import com.fundamentosplatzi.fundamentos.service.UserService;

@Component
public class DeleteUser {
	UserService userService;
	
	public DeleteUser(UserService userService) {
		this.userService = userService;
	}
	
	public void remove(Long id) {
		userService.deleteUser(id);
	}
	
}
