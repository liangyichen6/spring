package com.practice.spring.transactiontest.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
	
	public int id;
	
	public String username;
	
	public int gender;
	
	public String password;
}
