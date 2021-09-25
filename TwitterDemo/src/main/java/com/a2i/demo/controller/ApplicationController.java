package com.a2i.demo.controller;

import com.a2i.demo.Constant.EmailTemplate;
import com.a2i.demo.DTO.EmailDTO;
import com.a2i.demo.entity.Appointment;
import com.a2i.demo.service.AppointmentService;
import com.a2i.demo.service.UserActionService;
import com.a2i.demo.service.serviceImpl.SmsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    public final static Logger logger = LogManager.getLogger(ApplicationController.class);

    @ResponseBody
    @RequestMapping("/send-email")
    public String sendEmail() {
        logger.info("Call rech to api: "+ "/send-email");
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
            logger.info("Call rech to api: "+ "api/v1/add/appointment");
            appointmentService.addAppointment(appointment);
        }catch (Exception e){
            return  e.getMessage();
        }
        return "success";
    }
}
