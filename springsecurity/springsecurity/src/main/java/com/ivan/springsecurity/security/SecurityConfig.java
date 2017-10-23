package com.ivan.springsecurity.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final static String DEFULAT_FILTER_PROCESSES_URL = "/login";

	@Override
	public void configure(HttpSecurity http) throws Exception {
		/*
		 * http.authorizeRequests().antMatchers("/css/**", "/index").permitAll()
		 * .antMatchers("/user/**").hasRole("USER").and().formLogin()
		 * .loginPage("/login").failureUrl("/login-error");
		 */

		// disable csrf
		http.csrf().ignoringAntMatchers("/login");

		// customization
		CustomizationUsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new CustomizationUsernamePasswordAuthenticationFilter(
				DEFULAT_FILTER_PROCESSES_URL);

		http.addFilterAfter(usernamePasswordAuthenticationFilter,
				LogoutFilter.class);

		// do let spring security create session
		http.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.NEVER);
	}
}
