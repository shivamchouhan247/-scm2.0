package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

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
}
