package com.practice.spring.transactiontest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice.spring.transactiontest.domain.User;
import com.practice.spring.transactiontest.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userServiceImpl;
	
	@PostMapping(value="/users")
	public ResponseEntity<String> addUser(@RequestBody User u) {
		this.userServiceImpl.addUser(u);
		return ResponseEntity.ok("success");
	}
	
	@PutMapping(value="/users")
	public ResponseEntity<String> addUserThenUpdate(@RequestBody User u) {
		this.userServiceImpl.addThenUpdate(u);;
		return ResponseEntity.ok("success");
	}
}
