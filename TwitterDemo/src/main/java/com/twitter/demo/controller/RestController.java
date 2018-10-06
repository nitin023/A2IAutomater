package com.twitter.demo.controller;

import com.twitter.demo.Services.UserService;
import com.twitter.demo.modal.User;
import com.twitter.demo.modal.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.Date;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private UserService userService;


    @GetMapping("/save-user")
    public String saveUser(@RequestParam String firstName, @RequestParam String middleName,
                           @RequestParam String lastName, @RequestParam String userName,
                           @RequestParam String emailId, @RequestParam String password,
                           @RequestParam String mobile, @RequestParam Date dateOfBirth,
                           @RequestParam String address) {

        Calendar dob = Calendar.getInstance();
        dob.set(dateOfBirth.getYear(), dateOfBirth.getMonth(), dateOfBirth.getDay());

        UserProfile profile = new UserProfile
                (firstName, middleName, lastName,
                        mobile, emailId, dob,
                        address, false);
        User user = new User(userName, password);
        user.setUserProfile(profile);
        profile.setUser(user);
        userService.saveUser(user);
        return "user saved";
    }
}
