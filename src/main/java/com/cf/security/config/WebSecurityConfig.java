package com.cf.security.config;

import com.cf.security.config.app.AppAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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

        http
                .addFilterAfter(appAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        //url权限验证
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
//        registry.anyRequest()
//                .access("@permissionService.hasPermission(request,authentication)");

    }

    @Bean
    public AppAuthenticationFilter appAuthenticationFilter(){
        AppAuthenticationFilter appAuthenticationFilter = new AppAuthenticationFilter();
        return appAuthenticationFilter;
    }

}
