package com.ivan.springcloud.usrmgt.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.authorizeRequests().anyRequest().permitAll();
	}
}
