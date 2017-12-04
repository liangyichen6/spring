package com.ivan.springcloud.auth;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/**
 * This token enhancer just for add additional inforamtion
 *
 * @author lchen283
 * @Date 2017-12-04
 */
public class AdditionalInformationTokenEnhancer implements TokenEnhancer {

    /**
     * add additional information into access token and refresh token
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken result = new DefaultOAuth2AccessToken(accessToken);
        Map<String, Object> info = new LinkedHashMap<>(result.getAdditionalInformation());

        // add user information into token
        User u = new User();
        u.setFirstName("Liangyi");
        u.setLastName("Chen");
        u.setUsername("Liangyi Chen");
        info.put("user", u);
        result.setAdditionalInformation(info);

        return result;
    }

}
