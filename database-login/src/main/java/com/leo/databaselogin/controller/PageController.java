package com.leo.databaselogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    /**
     * 主页
     * @return
     */
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 系统日志管理
     * @return
     */
    @GetMapping("/syslog")
    public String systemLog(){
        return "syslog";
    }

    /**
     * 业务一
     * @return
     */
    @GetMapping("/businessOne")
    public String businessOne(){
        return "businessOne";
    }

    /**
     * 业务二
     * @return
     */
    @GetMapping("/businessTwo")
    public String businessTwo(){
        return "businessTwo";
    }

    /**
     * 用户管理
     * @return
     */
    @GetMapping("/sysuser")
    public String sysUser(){
        return "sysuser";
    }

   /* @PostMapping("/login")
    public String login(String username,String password){
        System.out.println("in ...........");
        return "/index";
    }*/
}
