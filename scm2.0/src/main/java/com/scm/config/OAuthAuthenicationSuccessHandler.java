package com.scm.config;

import com.scm.entities.Provider;
import com.scm.entities.User;
import com.scm.mapper.AppConstant;
import com.scm.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class OAuthAuthenicationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthAuthenicationSuccessHandler.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("OAuthAuthenicationSuccessHandler");


        var oauth2AuthenicationToken = (OAuth2AuthenticationToken) authentication;

        String authorizedClientRegistrationId = oauth2AuthenicationToken.getAuthorizedClientRegistrationId();

        logger.info(authorizedClientRegistrationId);

        var oauthUser = (DefaultOAuth2User) authentication.getPrincipal();

        oauthUser.getAttributes().forEach((key, value) -> {
            logger.info(key + " : " + value);
        });

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setRoleList(List.of(AppConstant.USER_ROLE));
        user.setEmailVerified(true);
        user.setEnabled(true);
        user.setPassword("dummy");

        if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {

            // google
            // google attributes

            user.setEmail(oauthUser.getAttribute("email"));
            user.setProfilePic(oauthUser.getAttribute("picture"));
            user.setFirstName(oauthUser.getAttribute("name"));
            user.setProviderUserId(oauthUser.getName());
            user.setProvider(Provider.GOOGLE);
            user.setAbout("This account is created using google.");

        } else if (authorizedClientRegistrationId.equalsIgnoreCase("github")) {

            // github
            // github attributes
            String email = oauthUser.getAttribute("email") != null ? oauthUser.getAttribute("email")
                    : oauthUser.getAttribute("login") + "@gmail.com";
            String picture = oauthUser.getAttribute("avatar_url");
            String name = oauthUser.getAttribute("login");
            String providerUserId = oauthUser.getName();

            user.setEmail(email);
            user.setProfilePic(picture);
            user.setFirstName(name);
            user.setProviderUserId(providerUserId);
            user.setProvider(Provider.GITHUB);

            user.setAbout("This account is created using github");
        } else if (authorizedClientRegistrationId.equalsIgnoreCase("linkedin")) {

        } else {
            logger.info("OAuthAuthenicationSuccessHandler: Unknown provider");
        }

        // save the user
        // facebook
        // facebook attributes
        // linkedin

        /*
         *
         *
         *
         * DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
         *
         * logger.info(user.getName());
         *
         * user.getAttributes().forEach((key, value) -> {
         * logger.info("{} => {}", key, value);
         * });
         *
         * logger.info(user.getAuthorities().toString());
         *
         * // data database save:
         *
         * String email = user.getAttribute("email").toString();
         * String name = user.getAttribute("name").toString();
         * String picture = user.getAttribute("picture").toString();
         *
         * // create user and save in database
         *
         * User user1 = new User();
         * user1.setEmail(email);
         * user1.setName(name);
         * user1.setProfilePic(picture);
         * user1.setPassword("password");
         * user1.setUserId(UUID.randomUUID().toString());
         * user1.setProvider(Providers.GOOGLE);
         * user1.setEnabled(true);
         *
         * user1.setEmailVerified(true);
         * user1.setProviderUserId(user.getName());
         * user1.setRoleList(List.of(AppConstants.ROLE_USER));
         * user1.setAbout("This account is created using google..");
         *
         * User user2 = userRepo.findByEmail(email).orElse(null);
         * if (user2 == null) {
         *
         * userRepo.save(user1);
         * logger.info("User saved:" + email);
         * }
         *
         */

        User user2 = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (user2 == null) {
            userRepository.save(user);
            System.out.println("user saved:" + user.getEmail());
        }

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");

      /*  DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();

        logger.info(oauthUser.getName());

        oauthUser.getAttributes().forEach((key, value) -> {
            logger.info("{}=>{}", key, value);
        });

        *//*String email = Objects.requireNonNull(oauthUser.getAttribute("email")).toString();
        String firstName = Objects.requireNonNull(oauthUser.getAttribute("firstName")).toString();
        String profilePic = Objects.requireNonNull(oauthUser.getAttribute("profilePic")).toString();
        String lastName = Objects.requireNonNull(oauthUser.getAttribute("lastName")).toString();*//*

        User user1 = new User();

        user1.setEmail(oauthUser.getAttribute("email"));
        user1.setFirstName(oauthUser.getAttribute("name"));
        user1.setLastName(oauthUser.getAttribute("name"));
        user1.setProfilePic(oauthUser.getAttribute("profilePic"));
        user1.setPassword("Password");
        user1.setUserId(UUID.randomUUID().toString());
        user1.setProvider(Provider.GOOGLE);
        user1.setEnabled(true);
        user1.setEmailVerified(true);
        user1.setProviderUserId(oauthUser.getName());
        user1.setRoleList(List.of(AppConstant.USER_ROLE));
        user1.setAbout("This account is created by Google");
        user1.setCreatedDate(new Date());

        User user2 = userRepository.findByEmail(user1.getEmail()).orElse(null);
        if (user2 == null) {
            userRepository.save(user1);
            logger.info("User Successfully Saved " + user1.getEmail());
        }


        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");*/
    }
}
