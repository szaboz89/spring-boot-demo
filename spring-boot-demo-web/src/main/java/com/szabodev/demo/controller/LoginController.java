package com.szabodev.demo.controller;

import com.szabodev.demo.configuration.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final AppConfig appConfig;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginController(AppConfig appConfig, AuthenticationManager authenticationManager) {
        this.appConfig = appConfig;
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping("/login")
    public String getLoginPage(HttpServletRequest request) {
        if ("test".equals(appConfig.getLoginMode())) {
            logger.info("Application running in TEST mode, authenticate automatically...");
            authenticateUserAndSetSession(request);
            return "redirect:/";
        }
        return "layout/login";
    }

    private void authenticateUserAndSetSession(HttpServletRequest request) {
        String username = appConfig.getAdminName();
        String password = appConfig.getAdminPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        request.getSession();
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
}
