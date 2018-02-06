package com.szabodev.demo.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    private final AppConfig appConfig;

    @Autowired
    public WebSecurityConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/", "/home", "/login", "/language/**").permitAll()
                .antMatchers("/rest/*", "/rest/*/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler((request, response, authentication) -> {
                    logger.info("User successfully logged out, username: " + authentication.getName());
                    response.sendRedirect("/");
                })
                .permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        if ("ldap".equals(appConfig.getLoginMode())) {
            auth
                    .ldapAuthentication()
                    .contextSource()
                    .url(appConfig.getLdapUrl() + appConfig.getLdapBaseDn())
                    .managerDn(appConfig.getLdapUsername())
                    .managerPassword(appConfig.getLdapPassword())
                    .and()
                    .userDnPatterns(appConfig.getLdapUserDnPattern());
        } else {
            auth
                    .inMemoryAuthentication()
                    .withUser(appConfig.getAdminName()).password("{noop}" + appConfig.getAdminPassword()).roles("ADMIN");
        }
    }
}
