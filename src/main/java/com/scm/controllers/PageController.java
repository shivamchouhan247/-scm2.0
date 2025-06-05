package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(Model model){
        model.addAttribute("heading","SCM HOME PAGE");
        model.addAttribute("owner","Shivam Chouhan");
        model.addAttribute("websiteLink","https://pagespeed.web.dev/");
        return "home";  
    }

    @RequestMapping("/about")
    public String aboutPage(Model model){
        model.addAttribute("isLogin", false);
        return "about";
    }

    @RequestMapping("/services")
    public String servicePage(){
        return "services";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }
    
}
