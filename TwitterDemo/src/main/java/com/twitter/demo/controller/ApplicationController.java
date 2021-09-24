package com.twitter.demo.controller;

import com.twitter.demo.Resources.Email.Constant.EmailTemplate;
import com.twitter.demo.Services.EmailService;
import com.twitter.demo.modal.Email;
import com.twitter.demo.sms.service.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {

    @Autowired
    EmailService emailService;

    @Autowired
    SmsSender smsSender;

    @RequestMapping("/send-email")
    public String sendEmail() {
        Email email = new Email("test email", "komal.sharma1@olx.com", EmailTemplate.APP_VERIFY);
        emailService.setSendMail(email);
        return "Email sent";
    }

    @RequestMapping("/sendSms/{toPhoneNumber}")
    public String sendSms(@PathVariable String toPhoneNumber)
    {
        smsSender.sendSMS(toPhoneNumber);
        return "Sms sent";
    }
}
