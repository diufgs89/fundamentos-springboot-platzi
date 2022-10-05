package com.fundamentosplatzi.fundamentos.caseuse;

import java.util.List;

import com.fundamentosplatzi.fundamentos.entity.User;
import com.fundamentosplatzi.fundamentos.service.UserService;

public class GetUserImplement implements GetUser {
	private UserService userService;
	
	//Inyasion de dependencia, ya no es necesario que lleve el @Autowire
	public GetUserImplement(UserService userService) {
		this.userService = userService;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userService.getAllUsers();
	}

}
