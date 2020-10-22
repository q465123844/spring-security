package com.leo.databaselogin.controller;

import com.leo.databaselogin.auth.domain.Person;
import com.leo.databaselogin.auth.impl.MethodELService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {
    @Resource
    private MethodELService methodELService;

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
    @GetMapping("/sys_log")
    public String systemLog(){
        return "sys_log";
    }

    /**
     * 业务一
     * @return
     */
    @GetMapping("/business_one")
    public String businessOne(){
        //one
        //methodELService.findAll();
        //two
        //methodELService.findOne();
        //three
        /*List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        methodELService.delete(ids,null);*/
        //若登录用户为admin,则list中的zhangsan会被过滤掉,只剩下admin
        //four
       /* List<Person> alls = methodELService.getAlls();
        System.out.println("alls:"+alls.size());*/
        return "business_one";
    }

    /**
     * 业务二
     * @return
     */
    @GetMapping("/business_two")
    public String businessTwo(){
        return "business_two";
    }

    /**
     * 用户管理
     * @return
     */
    @GetMapping("/sys_user")
    public String sysUser(){
        return "sys_user";
    }

   /* @PostMapping("/login")
    public String login(String username,String password){
        System.out.println("in ...........");
        return "/index";
    }*/
}
