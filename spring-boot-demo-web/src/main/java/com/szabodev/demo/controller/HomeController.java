package com.szabodev.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String getMain() {
        return "home";
    }

    @RequestMapping("/")
    public String getRoot() {
        return "home";
    }

}
