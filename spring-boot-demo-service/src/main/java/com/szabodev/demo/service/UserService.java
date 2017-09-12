package com.szabodev.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService implements ApplicationListener<AuthenticationSuccessEvent> {

    private final SessionStorage sessionStorage;

    @Autowired
    public UserService(SessionStorage sessionStorage) {
        this.sessionStorage = sessionStorage;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String userName = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();
        sessionStorage.setUserName(userName);
        sessionStorage.setLoginTime(LocalDateTime.now());
    }
}