package spring.ivan.springaop.service;

import org.springframework.stereotype.Service;

import spring.ivan.springaop.bean.User;

@Service
public class UserServiceImpl {

	public User login(String username, String password) {
		System.out.println("Executing login method");
		User u = new User();
		u.setFirstName("Emon");
		u.setLastName("Huang");
		u.setUsername("Emon Huang");
		return u;
	}

	public void updateUser() throws Exception {
		System.out.println("Executing update user...");
		throw new Exception("Update user failed");
	}

	public void deleteUser(Long userId) {
		System.out.println(userId);
	}
}
