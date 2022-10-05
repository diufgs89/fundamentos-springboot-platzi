package com.fundamentosplatzi.fundamentos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fundamentosplatzi.fundamentos.caseuse.GetUser;
import com.fundamentosplatzi.fundamentos.caseuse.GetUserImplement;
import com.fundamentosplatzi.fundamentos.service.UserService;

@Configuration
public class CaseUseConfiguration {

	@Bean
	GetUser getUser(UserService userservice) {
		return new GetUserImplement(userservice);
	}
	
}
