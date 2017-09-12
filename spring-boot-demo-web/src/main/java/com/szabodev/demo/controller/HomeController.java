package com.szabodev.demo.controller;

import com.szabodev.demo.service.SessionStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private final SessionStorage sessionStorage;

    @Autowired
    public HomeController(SessionStorage sessionStorage) {
        this.sessionStorage = sessionStorage;
    }

    @RequestMapping("/home")
    public String getMain() {
        return "home";
    }

    @RequestMapping("/")
    public String getRoot() {
        return "home";
    }

    @RequestMapping("/mypage")
    public String getMyPage(Model model) {
        model.addAttribute("loggedUser", sessionStorage.getUserName());
        model.addAttribute("loginTime", sessionStorage.getLoginTime());
        return "mypage";
    }

}
