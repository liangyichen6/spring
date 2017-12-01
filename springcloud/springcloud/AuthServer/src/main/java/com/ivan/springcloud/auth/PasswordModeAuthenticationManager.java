package com.ivan.springcloud.auth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Scope("singleton")
public class PasswordModeAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return null;
        }

        if (username.equals("test") && password.equals("test")) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("admin"));
            return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                                                           authentication.getCredentials(),
                                                           Collections.unmodifiableList(authorities));
        }

        return null;
    }

}
