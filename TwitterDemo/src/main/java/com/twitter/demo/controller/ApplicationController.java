package com.twitter.demo.controller;

import com.twitter.demo.Constant.EmailTemplate;
import com.twitter.demo.DTO.BookedLeadsDto;
import com.twitter.demo.service.serviceImpl.EmailServiceImpl;
import com.twitter.demo.service.AppointmentService;
import com.twitter.demo.service.IWAMessageService;
import com.twitter.demo.service.UserActionService;
import com.twitter.demo.model.Appointment;
import com.twitter.demo.model.Email;
import com.twitter.demo.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {

    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    SmsService smsService;

    @Autowired
    UserActionService userActionService;

    @Autowired
    IWAMessageService waMessageService;

    @Autowired
    AppointmentService appointmentService;

    @ResponseBody
    @RequestMapping("/send-email")
    public String sendEmail() {
        Email email = new Email("test email", "komal.sharma1@olx.com", EmailTemplate.APP_VERIFY);
       // emailService.setSendMail(email);
        return "Email sent";
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

    @PostMapping("/api/v1/add/appointment")
    public String addAppointments(@RequestBody Appointment appointment){
        try {
            appointmentService.addAppointment(appointment);
        }catch (Exception e){
            return  e.getMessage();
        }
        return "success";
    }
}
