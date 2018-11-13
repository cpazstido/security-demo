package com.cf.security.config.app;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;

public class AppAuthenticationProvider implements AuthenticationProvider{
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //根据token从redis中获取用户信息
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("admin");
        User user = new User("admin","admin", Arrays.asList(authority));
        AppAuthenticationToken appAuthenticationToken = new AppAuthenticationToken(user,Arrays.asList(authority));
        return appAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AppAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
