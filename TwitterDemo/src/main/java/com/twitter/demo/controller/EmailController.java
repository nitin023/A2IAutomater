package com.twitter.demo.controller;


import com.twitter.demo.Services.UserActionService;
import com.twitter.demo.model.UserAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailController {

    @Autowired
    UserActionService userActionService;

    @GetMapping("/register")
    public String showForm(Model model) {
        UserAction userAction = new UserAction();
        model.addAttribute("userAction", userAction);
        return "UserInteractiveTemplate";
    }

    @PostMapping("/api/v1/add/actions")
    public String submitForm(@ModelAttribute("userAction") UserAction userAction) {
        try {
            userActionService.insertUserAction(userAction);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }
}
