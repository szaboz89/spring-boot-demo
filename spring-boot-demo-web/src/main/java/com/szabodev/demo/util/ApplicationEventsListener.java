package com.szabodev.demo.util;

import com.szabodev.demo.service.SessionStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApplicationEventsListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationEventsListener.class);

    private final SessionStorage sessionStorage;

    @Autowired
    public ApplicationEventsListener(SessionStorage sessionStorage) {
        this.sessionStorage = sessionStorage;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String username = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();
        logger.info("User successfully logged in, username: " + username);
        sessionStorage.setUsername(username);
        sessionStorage.setLoginTime(LocalDateTime.now());
    }
}