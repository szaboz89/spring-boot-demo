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

    @Value("${ldap.url}")
    private String ldapUrl;

    @Value("${ldap.base.dn}")
    private String ldapBaseDn;

    @Value("${ldap.username}")
    private String ldapUsername;

    @Value("${ldap.password}")
    private String ldapPassword;

    @Value("${ldap.user.dn.pattern}")
    private String ldapUserDnPattern;

    public String getAdminName() {
        return adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public String getLoginMode() {
        return loginMode;
    }

    String getLdapUrl() {
        return ldapUrl;
    }

    String getLdapBaseDn() {
        return ldapBaseDn;
    }

    String getLdapUsername() {
        return ldapUsername;
    }

    String getLdapPassword() {
        return ldapPassword;
    }

    String getLdapUserDnPattern() {
        return ldapUserDnPattern;
    }

    /**
     * Returns the application version string when you run the jar
     * When you run from IntelliJ it returns 'Local'
     *
     * @return the Implementation-Version defined in the MANIFEST.MF
     */
    public String getAppVersion() {
        String version = getClass().getPackage().getImplementationVersion();
        return version != null ? version : "Local";
    }

}
