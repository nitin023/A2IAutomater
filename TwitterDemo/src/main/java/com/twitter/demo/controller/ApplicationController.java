package com.twitter.demo.controller;

import com.twitter.demo.DTO.UserActionRepository;
import com.twitter.demo.Resources.Email.Constant.EmailTemplate;
import com.twitter.demo.Services.EmailService;
import com.twitter.demo.Services.UserActionService;
import com.twitter.demo.modal.Email;
import com.twitter.demo.modal.UserAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

@Controller
public class ApplicationController {

    @Autowired
    EmailService emailService;

    @Autowired
    UserActionService userActionService;

    @ResponseBody
    @RequestMapping("/send-email")
    public String sendEmail() {
        Email email = new Email("test email", "komal.sharma1@olx.com", EmailTemplate.APP_VERIFY);
        emailService.setSendMail(email);

        email = new Email("test email", "vipul.pachauri@olx.com", EmailTemplate.APP_VERIFY);
        emailService.setSendMail(email);

        email = new Email("test email", "dharmendra.singh@olx.com", EmailTemplate.APP_VERIFY);
        emailService.setSendMail(email);
        return "Email sent";
    }

    @PostMapping("/api/v1/add/actions")
    public String receiveActions(@RequestBody UserAction userAction){
        try {
            userActionService.insertUserAction(userAction);
        }catch (Exception e){
            return e.getMessage();
        }
        return "success";


    }
}
