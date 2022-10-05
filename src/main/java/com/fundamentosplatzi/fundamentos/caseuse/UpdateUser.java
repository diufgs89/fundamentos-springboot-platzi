package com.fundamentosplatzi.fundamentos.caseuse;

import org.springframework.stereotype.Component;

import com.fundamentosplatzi.fundamentos.entity.User;
import com.fundamentosplatzi.fundamentos.service.UserService;

@Component
public class UpdateUser {
	UserService userService;
	
	public UpdateUser(UserService userService) {
		this.userService = userService;
	}
	
	public User update(User user, Long id) {
		return 	userService.updateUser(user, id);
	}
}
