package com.a2i.demo.controller;


import com.a2i.demo.entity.UserAction;
import com.a2i.demo.service.UserActionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    Logger logger = LogManager.getLogger(EmailController.class);
    @GetMapping("/register")
    public String showForm(Model model) {
        UserAction userAction = new UserAction();
        model.addAttribute("userAction", userAction);
        return "UserInteractiveTemplate";
    }

    @PostMapping("/api/v1/add/actions")
    public String submitForm(@ModelAttribute("userAction") UserAction userAction) {
        try {
            logger.info("inside : "+ "api/v1/add/actions");
            userActionService.insertUserAction(userAction);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }
}
