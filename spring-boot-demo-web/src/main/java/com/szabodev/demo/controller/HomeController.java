package com.szabodev.demo.controller;

import com.szabodev.demo.configuration.AppConfig;
import com.szabodev.demo.service.SessionStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

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
        return "util/home";
    }

    @RequestMapping("/")
    public String getRoot() {
        return "util/home";
    }

    @RequestMapping("/about")
    public String getAbout(Model model) {
        model.addAttribute("loggedUser", sessionStorage.getUsername());
        model.addAttribute("loginTime", sessionStorage.getLoginTime());
        model.addAttribute("appVersion", appConfig.getAppVersion());
        return "util/about";
    }

    @RequestMapping("/language/{code}")
    public String changeLanguage(@PathVariable String code, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        logger.debug("changeLanguage called, language code: {}", code);
        this.localeResolver.setLocale(httpServletRequest, httpServletResponse, new Locale(code));
        return "redirect:/";
    }

}
