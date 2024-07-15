package com.scm.controller;

import com.scm.entities.User;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.mapper.UserForm;
import com.scm.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("name", "Ansar");
        model.addAttribute("address", "Najiababd");
        model.addAttribute("link", "https://www.youtube.com/watch?v=3p__WB2Kuls&list=PL0zysOflRCemNS641tpw66bcaFylyIGsA&index=3");
        System.out.println("Home page controller...");
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage() {
        System.out.println("About page loading ..........");
        return "about";

    }

    @RequestMapping("/services")
    public String servicePage() {
        System.out.println("Service page loading ..........");
        return "service";

    }

    @RequestMapping("/contact")
    public String contactPage() {
        System.out.println("Contact page loading ..........");
        return "contact";
    }

    @RequestMapping("/login")
    public String loginPage() {
        System.out.println("login page loading ..........");
        return "login";
    }

    @RequestMapping("/signup")
    public String signupPage(Model model) {

        UserForm userForm = new UserForm();
        model.addAttribute("user", userForm);

        return "signup";
    }

    /**
     * this method is used to signup registration
     */

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String signupProcessing(@Valid @ModelAttribute UserForm userForm, HttpSession session, BindingResult bindingResult) {
       /* User buildUser = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .phoneNumber(userForm.getPhoneNumber())
                .profilePic("https://search.app.goo.gl/DCMfKL7")
                .createdDate(new Date())
                .about(userForm.getAbout()).build();*/

        if(bindingResult.hasErrors()){
            return "signup";
        }
        User user= new User();
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://search.app.goo.gl/DCMfKL7");
        user.setCreatedDate(new Date());
        user.setAbout(userForm.getAbout());
        userService.saveUser(user);

        Message message = Message.builder().content("Registration is successfull").type(MessageType.GREEN).build();
        session.setAttribute("message",message);
        return "redirect:/signup";
    }
}
