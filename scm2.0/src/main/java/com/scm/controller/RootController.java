package com.scm.controller;

import com.scm.entities.User;
import com.scm.helper.Helper;
import com.scm.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class RootController {

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(RootController.class);

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {
        if (authentication==null){
            return;
        }
        String userName = Helper.emailOfLoggedInUser(authentication);
        logger.info("User logged in :{}", userName);
        User loginUser = userService.getUserByEmail(userName);
        logger.info("User Name : {}", loginUser.getFirstName());
        logger.info("User Email :{}", loginUser.getEmail());
        model.addAttribute("loggedInUser", loginUser);


    }
}
