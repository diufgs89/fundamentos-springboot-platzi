package com.fundamentosplatzi.fundamentos.service;

import org.apache.commons.logging.LogFactory;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundamentosplatzi.fundamentos.entity.User;
import com.fundamentosplatzi.fundamentos.repository.UserRepository;

@Service
public class UserService {
	private final Log LOG = LogFactory.getLog(UserService.class);
	private UserRepository userRepository;
	
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	//@Transactional
	public void saveTransactional(List<User> users) {
		users.stream().peek(user -> LOG.info("Usuario INSERT : " + user))
		.forEach(userRepository::save);
		//.forEach(user -> userRepository.save(user));
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User saveUser(User newUser) {
		return userRepository.save(newUser);
	}
	
	public void deleteUser(Long id) {
		userRepository.delete(new User(id));
	}
	
	public User updateUser(User userUpdate, Long id) {
		return userRepository.findById(id).
			map(
					user -> {
						user.setEmail(userUpdate.getEmail());
						user.setName(userUpdate.getName());
						user.setBirthDate(userUpdate.getBirthDate());
						return userRepository.save(user);
					}
				).get();
			
	}
	

}
