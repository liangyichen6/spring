package com.ivan.springcloud.auth;

import java.security.KeyPair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@SpringBootApplication
@EnableWebSecurity
public class AuthenticationServer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(AuthenticationServer.class).run(args);
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private TokenStore jwtTokenStore;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.inMemory()
                   .withClient("acme")
                   .secret("acmesecret")
                   .authorizedGrantTypes("client_credentials", "refresh_token")
                   .scopes("read")
                   .and()
                   .withClient("usrmgt")
                   .secret("usrmgtsecret")
                   .authorizedGrantTypes("client_credentials", "refresh_token")
                   .resourceIds("image")
                   .scopes("read")
                   .and()
                   .withClient("image")
                   .secret("image_secret")
                   .authorizedGrantTypes("client_credentials", "refresh_token")
                   .scopes("read", "wirte")
                   .resourceIds("usrmgt")
                   .and()
                   .withClient("test")
                   .secret("testSecret")
                   .authorizedGrantTypes("password", "refresh_token")
                   .resourceIds("test")
                   .scopes("read");

        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.authenticationManager(authenticationManager)
                     .tokenStore(jwtTokenStore)
                     .accessTokenConverter(jwtAccessTokenConverter())
                     .userDetailsService(new CustomUserDetailsService());
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer.tokenKeyAccess("isAuthenticated()")
                       .checkTokenAccess("isAuthenticated()");
        }

        @Bean
        @Scope("singleton")
        public TokenStore getJwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        private JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "123456".toCharArray()).getKeyPair("pip");
            converter.setKeyPair(keyPair);
            return converter;
        }
    }

}
