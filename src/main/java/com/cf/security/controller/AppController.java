package com.cf.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app")
public class AppController {

    @RequestMapping("index")
    public String appIndex(){
        return "hello app";
    }
}
