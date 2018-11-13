package com.cf.security.config.app;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;

public class AppAuthenticationFilter extends GenericFilterBean {
    private RequestMatcher requiresAuthenticationRequestMatcher;

    public AppAuthenticationFilter() {
        requiresAuthenticationRequestMatcher = new AntPathRequestMatcher("/app/**", null);
    }

    protected boolean requiresAuthentication(HttpServletRequest request) {
        return requiresAuthenticationRequestMatcher.matches(request);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if(requiresAuthentication(request)){
            if(request.getHeader("token").compareTo("123")==0){
                //根据token从redis中获取用户信息
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority("admin");
                User user = new User("admin","admin", Arrays.asList(authority));
                AppAuthenticationToken appAuthenticationToken = new AppAuthenticationToken(user,Arrays.asList(authority));
                SecurityContextHolder.getContext().setAuthentication(appAuthenticationToken);
            }else {
                throw new AccessDeniedException("无权限");
            }
        }
        filterChain.doFilter(request,response);
    }
}
