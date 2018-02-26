package com.practice.spring.transactiontest.service;

import com.practice.spring.transactiontest.domain.User;

public interface UserService {
	
	public void addUser(User u);
	
	public void addThenUpdate(User u);
}
