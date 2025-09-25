package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {

    //user dashboard page controller
    @RequestMapping(value="/dashboard")
    public String userDashboard(){

        return "user/dashboard";
    }

    //user profile dashboard controller
    @RequestMapping(value = "/profile")
    public String userProfile(){
        return "user/profile";
    }

    //user add contact page

    //user view contact

    //user edit contact page

    //update contact

    //user delete contact page
    
}
