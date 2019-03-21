package com.cf.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {
    static Map<String,Authentication> UUID_MAP = new HashMap();
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

    /**
     * APP扫二维码访问的url
     * @param uuid
     * @return
     */
    @GetMapping("loginUser/{uuid}")
    public Authentication setLoginUser(@PathVariable("uuid") String uuid){
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        UUID_MAP.put(uuid,user);
        return user;
    }

    /**
     * 返回一个uuid，前端根据这个uuid来获取二维码
     * @return
     */
    @GetMapping("getUUID")
    public String uuid(){
        return UUID.randomUUID().toString();
    }

    /**
     * 返回一个二维码让用户扫
     * @param uuid
     * @return
     */
    @GetMapping("getCode/{uuid}")
    public String code(@PathVariable("uuid") String uuid){
        return "http://localhost:8080/loginUser/"+uuid;
    }

    /**
     * 用户循环验证登录的url
     * @param uuid
     * @return
     */
    @GetMapping("qrCode/{uuid}")
    public String qrCode(@PathVariable("uuid") String uuid){
        for(int i=0;i<25;i++){
            Authentication user = UUID_MAP.get(uuid);
            if(user!=null){
                SecurityContextHolder.getContext().setAuthentication(user);
                return "login success";
            }else{
                try {
                    Thread.sleep(1000);
                    System.out.println("休息"+i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return "login timeout";
    }
}
