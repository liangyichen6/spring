package com.ivan.springsecurity.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.HeaderWriterFilter;

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

		http.authorizeRequests().antMatchers("/login").permitAll().anyRequest()
				.authenticated();

		// protect csrf attack
		// make sure csrf token expired date more than session expired date
		// http.csrf().ignoringAntMatchers("/login")
		// .csrfTokenRepository(new CookieCsrfTokenRepository());

		// disable csrf protection by spring security
		http.csrf().disable();

		// customization usernamePasswordAuthenticationFilter
		CustomizationUsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new CustomizationUsernamePasswordAuthenticationFilter(
				DEFULAT_FILTER_PROCESSES_URL);

		// fix session fixation attack
		usernamePasswordAuthenticationFilter.setSessionAuthenticationStrategy(
				new SessionFixationProtectionStrategy());

		usernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(
				new CustomizationUsernamePasswordAuthenticationFailureHandler());

		http.addFilterAfter(usernamePasswordAuthenticationFilter,
				LogoutFilter.class);

		// customization csrf filter
		CustomizationCsrfFilter csrfFilter = new CustomizationCsrfFilter(
				new CookieCsrfTokenRepository());

		http.addFilterAfter(csrfFilter, HeaderWriterFilter.class);

		// disable request cache configurer
		http.requestCache().disable();
	}
}
