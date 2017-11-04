package com.ivan.springcloud.auth;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@SpringBootApplication
public class AuthenticationServer {

	public static void main(String[] args) {
		new SpringApplicationBuilder(AuthenticationServer.class).run(args);
	}

	@Configuration
	@EnableAuthorizationServer
	protected static class OAuth2Config
			extends AuthorizationServerConfigurerAdapter {

		@Autowired
		private AuthenticationManager authenticationManager;

		@Configuration
		@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
		protected static class LoginConfig
				extends WebSecurityConfigurerAdapter {

			@Autowired
			private AuthenticationManager authenticationManager;

			@Override
			protected void configure(HttpSecurity http) throws Exception {
				http.authorizeRequests().anyRequest().permitAll();
			}

			@Override
			protected void configure(AuthenticationManagerBuilder auth)
					throws Exception {
				auth.parentAuthenticationManager(authenticationManager);
			}
		}

		@Bean
		public JwtAccessTokenConverter jwtAccessTokenConverter() {
			JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
			KeyPair keyPair = new KeyStoreKeyFactory(
					new ClassPathResource("keystore.jks"),
					"foobar".toCharArray()).getKeyPair("test");
			converter.setKeyPair(keyPair);
			return converter;
		}

		@Override
		public void configure(ClientDetailsServiceConfigurer clients)
				throws Exception {
			clients.inMemory().withClient("acme").secret("acmesecret")
					.authorizedGrantTypes("authorization_code", "refresh_token",
							"password")
					.scopes("openid");
		}

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints)
				throws Exception {
			endpoints.authenticationManager(authenticationManager)
					.accessTokenConverter(jwtAccessTokenConverter());
		}

		@Override
		public void configure(AuthorizationServerSecurityConfigurer oauthServer)
				throws Exception {
			oauthServer.tokenKeyAccess("permitAll()")
					.checkTokenAccess("isAuthenticated()");
		}

	}
}
