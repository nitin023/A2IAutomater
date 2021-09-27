package com.a2i.demo.controller;


import com.a2i.demo.service.serviceImpl.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inspection")
public class InspectionTaskController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public void executeInspection(){
        bookingService.inspect();
    }
}
