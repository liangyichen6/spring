package com.ivan.springcloud.usrmgt.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

@Configuration
public class OAuth2RestTemplateConfiguration {

	@Bean("oauth2RestTemplate")
	public OAuth2RestTemplate oAuth2RestTemplate() {
		ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
		resource.setAccessTokenUri("http://localhost:8080/oauth/token");
		resource.setClientId("usrmgt");
		resource.setClientSecret("usrmgtsecret");
		resource.setScope(Arrays.asList("read"));
		resource.setId("usrmgt");
		resource.setGrantType("client_credentials");

		final OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(
				resource, new DefaultOAuth2ClientContext());
		oAuth2RestTemplate
				.setRequestFactory(new Netty4ClientHttpRequestFactory());

		return oAuth2RestTemplate;

	}
}
