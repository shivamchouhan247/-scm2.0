package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(){
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
