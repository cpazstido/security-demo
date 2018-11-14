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

    /**
     * 死循环
     * @return
     */
    @RequestMapping("for")
    public String fora(){
        int i=0;
        while(true){
            System.out.println(i++);
        }

    }
}
