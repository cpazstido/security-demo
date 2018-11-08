package com.cf.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/login1")
                .and()
                .authorizeRequests()
                .antMatchers("/login","/qrCode/**","/getCode/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(new UnauthorizedEntryPoint())
                .accessDeniedHandler(accessDeniedHandler)
        ;

        //url权限验证
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
//        registry.anyRequest()
//                .access("@permissionService.hasPermission(request,authentication)");

//        //开启form登录，并设置需要拦截的地址
//        http
//                .formLogin()
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//        ;
    }
}
