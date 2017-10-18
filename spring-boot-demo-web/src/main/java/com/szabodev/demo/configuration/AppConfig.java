package com.szabodev.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Component
@PropertySources({
        @PropertySource("classpath:default.properties"),
        @PropertySource(value = "file:app.properties", ignoreResourceNotFound = true)
})
public class AppConfig {

    @Value("${admin.name}")
    private String adminName;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${login.mode}")
    private String loginMode;

    public String getAdminName() {
        return adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public String getLoginMode() {
        return loginMode;
    }

    /**
     * Returns the application version string when you run the jar
     * When you run from IntelliJ it returns null
     *
     * @return the Implementation-Version defined in the MANIFEST.MF
     */
    public String getAppVersion() {
        return getClass().getPackage().getImplementationVersion();
    }

}
