package com.twitter.demo.controller;


import com.twitter.demo.Services.InspectionTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inspection")
public class InspectionTaskController {

    @Autowired
    private InspectionTaskService inspectionTaskService;

    @GetMapping
    public void executeInspection(){
        inspectionTaskService.inspect();
    }
}
