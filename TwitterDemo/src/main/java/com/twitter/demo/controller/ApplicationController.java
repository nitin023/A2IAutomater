package com.twitter.demo.controller;

import com.twitter.demo.Resources.Email.Constant.EmailTemplate;
import com.twitter.demo.Services.EmailService;
import com.twitter.demo.modal.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

@Controller
public class ApplicationController {

    @Autowired
    EmailService emailService;

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
}
