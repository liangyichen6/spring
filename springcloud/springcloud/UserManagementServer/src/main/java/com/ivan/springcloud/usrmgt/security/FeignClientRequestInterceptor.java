package com.ivan.springcloud.usrmgt.security;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignClientRequestInterceptor implements RequestInterceptor {

	private static final String AUTHORIZATION_HEADER = "Authorization";

	private static final String BEARER_TOKEN_TYPE = "Bearer";

	private final OAuth2RestTemplate oAuth2RestTemplate;

	public FeignClientRequestInterceptor(
			OAuth2RestTemplate oAuth2RestTemplate) {

		this.oAuth2RestTemplate = oAuth2RestTemplate;
	}

	@Override
	public void apply(RequestTemplate template) {
		System.out.println(oAuth2RestTemplate.getAccessToken());
		template.header(AUTHORIZATION_HEADER,
				String.format("%s %s", BEARER_TOKEN_TYPE,
						oAuth2RestTemplate.getAccessToken().toString()));

	}

}
