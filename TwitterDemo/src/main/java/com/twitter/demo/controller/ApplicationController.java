package com.twitter.demo.controller;

import com.twitter.demo.Constant.EmailTemplate;
import com.twitter.demo.DTO.BookedLeadsDto;
import com.twitter.demo.Services.EmailService;
import com.twitter.demo.Services.IWAMessageService;
import com.twitter.demo.Services.UserActionService;
import com.twitter.demo.modal.Email;
import com.twitter.demo.modal.UserAction;
import com.twitter.demo.sms.service.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {

    @Autowired
    EmailService emailService;

    @Autowired
    SmsSender smsSender;

    @Autowired
    UserActionService userActionService;

    @Autowired
    IWAMessageService waMessageService;

    @ResponseBody
    @RequestMapping("/send-email")
    public String sendEmail() {
        Email email = new Email("test email", "komal.sharma1@olx.com", EmailTemplate.APP_VERIFY);
       // emailService.setSendMail(email);
        return "Email sent";
    }

    @PostMapping("/api/v1/add/actions")
    public String receiveActions(@RequestBody UserAction userAction) {
        try {
            userActionService.insertUserAction(userAction);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";


    }

    @RequestMapping("/sendSms/{toPhoneNumber}")
    public String sendSms(@PathVariable String toPhoneNumber) {
       // smsSender.sendSMS(toPhoneNumber);
        return "Sms sent";
    }

    @ResponseBody
    @PostMapping(value = "/send-wa-message")
    public String sendMessage(@RequestBody BookedLeadsDto bookedLeadsDto) {
        waMessageService.sendWAMessage(bookedLeadsDto);

        return "message sent successfully";
    }
}
