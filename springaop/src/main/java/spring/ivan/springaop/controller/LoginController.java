package spring.ivan.springaop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.ivan.springaop.bean.User;
import spring.ivan.springaop.service.UserServiceImpl;

@RestController
public class LoginController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@PostMapping(value = "/login")
	public ResponseEntity<User> login(String username, String password) {

		return ResponseEntity.ok()
				.body(userServiceImpl.login(username, password));
	}
}
