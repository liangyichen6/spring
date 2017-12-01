package com.ivan.springcloud.auth;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtTokenOperationController {

    @Autowired
    private TokenStore jwtTokenStore;

    @DeleteMapping("/oauth/token/delete")
    public String removeToken(@RequestParam("token") String token) {

        jwtTokenStore.removeAccessToken(new DefaultOAuth2AccessToken(token));
        jwtTokenStore.removeRefreshToken(new DefaultOAuth2RefreshToken(token));

        Collection<OAuth2AccessToken> tokens = jwtTokenStore.findTokensByClientId("test");

        for (OAuth2AccessToken item : tokens) {
            System.out.println(item.getValue());
        }

        return "remove token successfully";
    }
}
