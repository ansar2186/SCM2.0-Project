package com.scm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/hi")
    public String home(){
        return "Welcome to my Home !";
    }
}
