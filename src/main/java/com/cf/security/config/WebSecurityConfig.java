package com.cf.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //开启form登录，并设置需要拦截的地址
        http
                .formLogin()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
        ;
    }
}
