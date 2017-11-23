package spring.ivan.springaop.service;

import org.springframework.stereotype.Service;

import spring.ivan.springaop.bean.User;

@Service
public class UserServiceImpl {

	public User login(String username, String password) {

		return new User();
	}
}
