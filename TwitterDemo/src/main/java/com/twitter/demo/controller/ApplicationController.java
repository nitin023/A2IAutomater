package com.twitter.demo.controller;

import com.twitter.demo.Resources.Email.Constant.EmailTemplate;
import com.twitter.demo.Services.EmailService;
import com.twitter.demo.modal.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApplicationController {

    @Autowired
    EmailService emailService;

    @ResponseBody
    @RequestMapping("/send-email")
    public String sendEmail() {
        Email email = new Email("test email", "komal.sharma1@olx.com", EmailTemplate.APP_VERIFY);
        emailService.setSendMail(email);
        return "Email sent";
    }
}
