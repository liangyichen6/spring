package com.ivan.springcloud.usrmgt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import feign.RequestInterceptor;

@Configuration
public class FeignClientRequestInterceptorConfiguration {

	@Autowired
	private OAuth2RestTemplate restTemplate;

	@Bean
	public RequestInterceptor oauth2RequestInterceptor() {

		return new FeignClientRequestInterceptor(restTemplate);
	}
}
