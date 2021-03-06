package com.hly.springbootSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {

    @RequestMapping("/")
    public String root(){
        return "redirect:/index";
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }



    //@RequestMapping将接收Get,Post,Head,Options等所有的请求方式
    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/loginError")
    public String loginError(ModelAndView modelAndView){
        modelAndView.addObject("loginError",true);
        return "login";
    }

    @RequestMapping("/admin")
    public String admin(){
        return "admin/admin";
    }

    //@RequestMapping(method = RequestMethod.GET)的缩写
    @GetMapping("401")
    public String error(){
        return "401";
    }


    @GetMapping("/logout")
    public String logout(){
        return "/";
    }

}
