package com.szabodev.demo.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionStorage {

    private LocalDateTime loginTime;
    private String userName;

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public String getUserName() {
        return userName;
    }

    void setUserName(String userName) {
        this.userName = userName;
    }
}
