package com.scm.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;


public class Helper {
    Logger logger= LoggerFactory.getLogger(Helper.class);

    public static String emailOfLoggedInUser(Authentication authentication) {
        System.out.println("----------------------Inside emailOfLoggedInUser method---------------------");

        if (authentication instanceof OAuth2AuthenticationToken) {
            var aOAuth2AuthenticationToken  = (OAuth2AuthenticationToken) authentication;
            var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            System.out.println("----------------------clientId---------------------");
            var oauth2User = (OAuth2User) authentication.getPrincipal();
            String username = "";
            if (clientId.equalsIgnoreCase("google")) {

                // sign with google

                System.out.println("----------------Getting email from google--------------------------");
                username = oauth2User.getAttribute("email");

            } else if (clientId.equalsIgnoreCase("github")) {

                // sign with github
                System.out.println("-----------------Getting email from github-----------------------------");
                username = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email")
                        : oauth2User.getAttribute("login") + "@gmail.com";
            }

            // sign with facebook
            return username;
        } else {
            return authentication.getName();
        }

    }
}
