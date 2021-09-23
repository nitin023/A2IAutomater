package com.twitter.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//TestCommit
@Controller
public class ApplicationController {

    @ResponseBody
    @RequestMapping ("/hello")
    public String Hello()
    {
        return "Hello from spring";
    }
}
