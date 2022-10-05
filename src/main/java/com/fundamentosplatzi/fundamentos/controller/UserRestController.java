package com.fundamentosplatzi.fundamentos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fundamentosplatzi.fundamentos.caseuse.CreateUser;
import com.fundamentosplatzi.fundamentos.caseuse.DeleteUser;
import com.fundamentosplatzi.fundamentos.caseuse.GetUser;
import com.fundamentosplatzi.fundamentos.caseuse.UpdateUser;
import com.fundamentosplatzi.fundamentos.entity.User;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	private GetUser getUser;
	private CreateUser createUser;
	private DeleteUser deleteUser;
	private UpdateUser updateUser;
	
	public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser) {
		this.getUser = getUser;
		this.createUser = createUser;
		this.deleteUser = deleteUser;
		this.updateUser = updateUser;	
	}
		
	@GetMapping("/")
	List<User> get(){
		return getUser.getAll();	
	}
	
	@PostMapping("/")
	ResponseEntity<User> newUser(@RequestBody User newUser){
		return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity deleteUser(@PathVariable Long id) {
		deleteUser.remove(id);		
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	ResponseEntity updateUser(@RequestBody User user, @PathVariable Long id) {
		return new ResponseEntity<>(updateUser.update(user, id), HttpStatus.OK);
	}
}
