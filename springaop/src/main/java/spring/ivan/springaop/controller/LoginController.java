package spring.ivan.springaop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.ivan.springaop.bean.ResultMessage;
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

	@PutMapping(value = "/users")
	public ResponseEntity<ResultMessage> updateUser() throws Exception {
		this.userServiceImpl.updateUser();
		ResultMessage rm = new ResultMessage();
		rm.setMessage("update user successfully");
		rm.setStatus(200);
		return ResponseEntity.ok().body(rm);
	}

	@DeleteMapping(value = "/users")
	public ResponseEntity<ResultMessage> deleteUser() {
		this.userServiceImpl.deleteUser(20l);
		ResultMessage rm = new ResultMessage();
		rm.setMessage("update user successfully");
		return ResponseEntity.ok(rm);
	}
}
