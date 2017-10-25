package com.ivan.springsecurity.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * When username password authentication failure fire this calss
 * 
 * @author liangyi
 * @Date 2017年10月25日
 */
public class CustomizationUsernamePasswordAuthenticationFailureHandler
		implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
				exception.getMessage());
	}
}
