package com.ivan.springcloud.usrmgt.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import feign.RequestInterceptor;

@Configuration
public class FeignClientOAuth2RequestInterceptor {

	@Autowired
	private OAuth2ClientContext oAuth2ClientContext;

	@Bean
	@Qualifier("oAuth2RequestInterceptor")
	public RequestInterceptor oAuth2RequestInterceptor() {

		ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
		resource.setAccessTokenUri("http://localhost:8080/auth/token");
		resource.setClientId("usrmgt");
		resource.setClientSecret("usrmgtsecret");
		resource.setScope(Arrays.asList("read"));
		resource.setGrantType("client_credentials");

		return new OAuth2FeignRequestInterceptor(oAuth2ClientContext, resource);
	}
}
