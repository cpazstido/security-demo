package com.cf.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
public class UserController {
    @GetMapping("user")
    public Principal getUsers(Principal principal){
        return principal;
    }

    @GetMapping("admin")
    public String hasAdminAuthority(){
        return "admin authority";
    }

    @RequestMapping("login")
    public ModelAndView shouldLogin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
