package com.cf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class SecurityDemoApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext cac = SpringApplication.run(SecurityDemoApplication.class, args);
        System.out.println("");
    }
}
