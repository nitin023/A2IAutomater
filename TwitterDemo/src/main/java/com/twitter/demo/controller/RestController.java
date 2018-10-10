package com.twitter.demo.controller;

import com.twitter.demo.DTO.UserDTO;
import com.twitter.demo.Resources.Email.Constant.EmailTemplate;
import com.twitter.demo.Services.EmailService;
import com.twitter.demo.Services.UserService;
import com.twitter.demo.modal.Email;
import com.twitter.demo.modal.User;
import com.twitter.demo.modal.UserContext;
import com.twitter.demo.modal.UserProfile;
import com.twitter.demo.repository.UserProfileRepository;
import com.twitter.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@org.springframework.web.bind.annotation.RestController
public class RestController extends Thread {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserProfileRepository userProfileRepository;

    @PostMapping("/addUser")
    public String saveUserInfo(@RequestBody UserContext userContext) {
        User user = userContext.getUser();
        UserProfile userProfile = userContext.getUserProfile();
        user.setUserProfile(userProfile);
        userProfile.setUser(user);

        userService.saveUser(user);
        StringBuilder sb = new StringBuilder();
        sb.append("A mail is send to ")
                .append(userProfile.getFirstName())
                .append("\t")
                .append(userProfile.getLastName())
                .append("\t").
                append("for verification");

        new Thread(() -> {
            Email email = new Email("Twitter account verification",
                    userProfile.getEmailId(),
                    EmailTemplate.APP_VERIFY);
            emailService.setSendMail(email);
        }).start();

        return sb.toString();
    }

    @PostMapping(value = "/getUser", produces = "application/json" , consumes = "application/json")
    public ResponseEntity<?> getUser(@RequestBody User user) {

        String username = user.getUserName();
        String password = user.getPassword() ;

        UserDTO userDTO = new UserDTO();
        for (User userIter : userRepository.findAll()) {
            if(userIter.getUserName().toLowerCase().contains(username))
            {
                if(userIter.getPassword().contains(password))
                {
                    userDTO.setUserName(userIter.getUserName());

                    UserProfile profile = userIter.getUserProfile();
                    userDTO.setFirstName(profile.getFirstName());
                    userDTO.setMiddleName(profile.getMiddleName());
                    userDTO.setLastName(profile.getLastName());
                    userDTO.setPhone(profile.getPhone());
                    userDTO.setAddress(profile.getAddress());
                    userDTO.setEmailId(profile.getEmailId());
                    userDTO.setPassword(userIter.getPassword());
                    userDTO.setPhone(profile.getPhone());
                    break;
                }
            }
        }
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/getAll", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> GetAllUsers() {

        List<UserDTO> lstUserDTO = new ArrayList<>();

        for (User user : userRepository.findAll()) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserName(user.getUserName());

            UserProfile profile = user.getUserProfile();
            userDTO.setFirstName(profile.getFirstName());
            userDTO.setMiddleName(profile.getMiddleName());
            userDTO.setLastName(profile.getLastName());
            userDTO.setPhone(profile.getPhone());
            userDTO.setAddress(profile.getAddress());
            userDTO.setEmailId(profile.getEmailId());

            lstUserDTO.add(userDTO);
        }

        return new ResponseEntity<List<UserDTO>>(lstUserDTO, HttpStatus.OK);
    }

    //this updates emailApproved field
    @PutMapping(value = "users/{userId}", produces = "application/json")
    public ResponseEntity<?> approveUserEmail(@PathVariable long userId) {
        User  userFound = userRepository.findByUserId(userId);
        UserProfile userProfile = userFound.getUserProfile();

        boolean response = false;
        if(!userProfile.isEmailApproved()) {
            userProfile.setEmailApproved(true);
            userFound.setUserProfile(userProfile);
            userRepository.save(userFound);
            response = true;
        }
        return new ResponseEntity(response,HttpStatus.OK);
    }


}
