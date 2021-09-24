package com.twitter.demo.controller;

import com.twitter.demo.Constant.EmailTemplate;
import com.twitter.demo.DTO.EmailDTO;
import com.twitter.demo.entity.Appointment;
import com.twitter.demo.service.AppointmentService;
import com.twitter.demo.service.UserActionService;
import com.twitter.demo.service.serviceImpl.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApplicationController {

    @Autowired
    SmsService smsService;

    @Autowired
    UserActionService userActionService;

    @Autowired
    AppointmentService appointmentService;

    @ResponseBody
    @RequestMapping("/send-email")
    public String sendEmail() {
        EmailDTO emailDTO = new EmailDTO("test email", "komal.sharma1@olx.com", EmailTemplate.APP_VERIFY);
       // emailService.setSendMail(email);
        return "Email sent";
    }

    @RequestMapping("/sendSms/{toPhoneNumber}")
    public String sendSms(@PathVariable String toPhoneNumber) {
       // smsSender.sendSMS(toPhoneNumber);
        return "Sms sent";
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
