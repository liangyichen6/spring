package com.practice.spring.transactiontest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.spring.transactiontest.Mapper.UserDao;
import com.practice.spring.transactiontest.domain.User;
import com.practice.spring.transactiontest.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public void addUser(User u) {
		userDao.addUser(u);
		throw new RuntimeException("insert should be failed");
	}

	@Transactional
	public void updateUser(User u) {
		userDao.updateUser(u);
	}
	
	@Override
	public void addThenUpdate(User u) {
		addUser(u);
		u.setUsername("Transactional");
		userDao.updateUser(u);
	}
}
