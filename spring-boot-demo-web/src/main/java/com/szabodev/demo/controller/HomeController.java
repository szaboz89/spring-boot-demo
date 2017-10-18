package com.szabodev.demo.controller;

import com.szabodev.demo.configuration.AppConfig;
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

    private final AppConfig appConfig;

    @Autowired
    public HomeController(SessionStorage sessionStorage, LocaleResolver localeResolver, AppConfig appConfig) {
        this.sessionStorage = sessionStorage;
        this.localeResolver = localeResolver;
        this.appConfig = appConfig;
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
        model.addAttribute("appVersion", appConfig.getAppVersion());
        return "about";
    }

    @RequestMapping("/language/{code}")
    public String editDeveloper(@PathVariable String code, HttpServletRequest httpServletRequest) {
        this.localeResolver.setLocale(httpServletRequest, null, new Locale(code));
        return "redirect:/";
    }

}
