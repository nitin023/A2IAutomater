package com.twitter.demo.controller;

import com.twitter.demo.Resources.Email.Constant.EmailTemplate;
import com.twitter.demo.Services.EmailService;
import com.twitter.demo.Services.UserService;
import com.twitter.demo.modal.Email;
import com.twitter.demo.modal.User;
import com.twitter.demo.modal.UserContext;
import com.twitter.demo.modal.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@org.springframework.web.bind.annotation.RestController
public class RestController extends Thread {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;


    @PostMapping("/addUser")
    public String saveUserInfo(@RequestBody UserContext userContext) {
        User user = userContext.getUser();
        UserProfile userProfile = userContext.getUserProfile();
        user.setUserProfile(userProfile);
        userProfile.setUser(user);

        userService.saveUser(user);
        StringBuilder sb = new StringBuilder();
        sb.append("A mail is send to ")
                .append(userProfile.getFirstName())
                .append("\t")
                .append(userProfile.getLastName())
                .append("\t").
                append("for verification");

        new Thread(() -> {
            Email email = new Email("Twitter account verification",
                    userProfile.getEmailId(),
                    EmailTemplate.APP_VERIFY);
            emailService.setSendMail(email);
        }).start();

        return sb.toString();
    }
}
