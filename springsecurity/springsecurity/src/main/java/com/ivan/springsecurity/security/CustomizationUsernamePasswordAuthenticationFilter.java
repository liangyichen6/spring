package com.ivan.springsecurity.security;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * customize the UsernamePasswordAuthenticationFilter
 * 
 * @author Administrator
 *
 */
public class CustomizationUsernamePasswordAuthenticationFilter
		extends AbstractAuthenticationProcessingFilter {

	public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
	public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";

	private String usernameParam = SPRING_SECURITY_FORM_USERNAME_KEY;
	private String passwordParam = SPRING_SECURITY_FORM_PASSWORD_KEY;

	private static ObjectMapper mapper = new ObjectMapper();

	protected CustomizationUsernamePasswordAuthenticationFilter(
			String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);

	}

	@Override
	protected AuthenticationManager getAuthenticationManager() {
		return new CustomizationUsernamePasswordAuthenticationManager();
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: "
							+ request.getMethod());
		}

		JsonNode bodyRequest = mapper.readTree(request.getReader());
		String username = bodyRequest.findValue(usernameParam).asText();
		String password = bodyRequest.findValue(passwordParam).asText();

		if (Objects.isNull(username)) {
			username = "";
		}

		if (Objects.isNull(password)) {
			password = "";
		}

		username = username.trim();

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, password);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		// if session is null create a session
		HttpSession session = request.getSession();

		if (logger.isDebugEnabled()) {
			logger.debug(
					"Authentication success. Updating SecurityContextHolder to contain: "
							+ authResult);
		}

		// set spring security information into session
		SecurityContextHolder.getContext().setAuthentication(authResult);

		// set user basic information into session, below is a example.
		session.setAttribute("", "abc");

		// Fire event
		if (this.eventPublisher != null) {
			eventPublisher.publishEvent(
					new InteractiveAuthenticationSuccessEvent(authResult,
							this.getClass()));
		}

		// TODO return object to front

	}

	public void setUsernameParameter(String usernameParameter) {
		Assert.hasText(usernameParameter, "Username must not be empty or null");
		this.usernameParam = usernameParameter;
	}

	public void setPasswordParameter(String passwordParameter) {
		Assert.hasText(passwordParameter, "Password must not be empty or null");
		this.passwordParam = passwordParameter;
	}

}
