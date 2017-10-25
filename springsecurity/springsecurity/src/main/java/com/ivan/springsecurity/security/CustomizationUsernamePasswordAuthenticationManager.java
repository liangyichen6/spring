package com.ivan.springsecurity.security;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomizationUsernamePasswordAuthenticationManager
		implements AuthenticationManager {

	private static final String DEFAULT_USERNAME = "username";

	private static final String DEFAULT_PASSWORD = "password";

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		if (!DEFAULT_USERNAME.equals(username)
				|| !DEFAULT_PASSWORD.equals(password))
			throw new BadCredentialsException("Username or password invalid.");

		return new UsernamePasswordAuthenticationToken(username, password,
				new ArrayList<>());
	}

}
