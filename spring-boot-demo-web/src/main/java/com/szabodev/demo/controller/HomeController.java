package com.szabodev.demo.controller;

import com.szabodev.demo.service.SessionStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
public class HomeController {

    private final SessionStorage sessionStorage;

    private final LocaleResolver localeResolver;

    @Autowired
    public HomeController(SessionStorage sessionStorage, LocaleResolver localeResolver) {
        this.sessionStorage = sessionStorage;
        this.localeResolver = localeResolver;
    }

    @RequestMapping("/home")
    public String getMain() {
        return "home";
    }

    @RequestMapping("/")
    public String getRoot() {
        return "home";
    }

    @RequestMapping("/about")
    public String getAbout(Model model) {
        model.addAttribute("loggedUser", sessionStorage.getUsername());
        model.addAttribute("loginTime", sessionStorage.getLoginTime());
        return "about";
    }

    @RequestMapping("/language/{code}")
    public String editDeveloper(@PathVariable String code, HttpServletRequest httpServletRequest) {
        this.localeResolver.setLocale(httpServletRequest, null, new Locale(code));
        return "redirect:/";
    }

}
