package com.cf.security.config.app;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AppAuthenticationToken extends AbstractAuthenticationToken{
    private final Object principal;

    public AppAuthenticationToken(String token) {
        super(null);
        this.principal = token;
        setAuthenticated(false);
    }

    public AppAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true); // must use super, as we override
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
