package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("name","Ansar");
        model.addAttribute("address","Najiababd");
        model.addAttribute("link","https://www.youtube.com/watch?v=3p__WB2Kuls&list=PL0zysOflRCemNS641tpw66bcaFylyIGsA&index=3");
        System.out.println("Home page controller...");
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage(){
        System.out.println("About page loding ..........");
        return "about";

    }

    @RequestMapping("/services")
    public String servicePage(){
        System.out.println("Service page loding ..........");
        return "service";

    }
    @RequestMapping("/contact")
    public String contactPage(){
        System.out.println("Contact page loding ..........");
        return "contact";
    }

    @RequestMapping("/login")
    public String loginPage(){
        System.out.println("login page loding ..........");
        return "login";
    }

    @RequestMapping("/signup")
    public String signupPage(){
        System.out.println("signup page loding ..........");
        return "signup";
    }
}
