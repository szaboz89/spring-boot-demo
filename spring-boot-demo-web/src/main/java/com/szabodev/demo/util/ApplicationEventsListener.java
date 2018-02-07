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
        UserDetails principal = (UserDetails) event.getAuthentication().getPrincipal();
        String username = principal.getUsername();
        logger.info("User successfully logged in, username: {}", username);
        logger.info("User's authorities:");
        principal.getAuthorities().forEach(o -> logger.info(o.toString()));
        sessionStorage.setUsername(username);
        sessionStorage.setLoginTime(LocalDateTime.now());
    }
}