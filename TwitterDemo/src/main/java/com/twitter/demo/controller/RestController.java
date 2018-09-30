package com.twitter.demo.controller;

import com.twitter.demo.Services.UserService;
import com.twitter.demo.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private UserService userService;

    @GetMapping("/save-user")
    public String saveUser(@RequestParam String firstName, @RequestParam String middleName,
                           @RequestParam String lastName, @RequestParam String userName,
                           @RequestParam String emailId, @RequestParam String password,
                           @RequestParam String mobile, @RequestParam String address) {

        User user = new User(firstName, middleName, lastName, userName, emailId, password, mobile, address);
        userService.saveUser(user);
        return "user saved";
    }
}
