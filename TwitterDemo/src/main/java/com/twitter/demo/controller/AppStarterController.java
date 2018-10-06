package com.twitter.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AppStarterController {

    @RequestMapping("/welcome")
    public String getWelcomeView ()
    {
        return "welcome";
    }
}
