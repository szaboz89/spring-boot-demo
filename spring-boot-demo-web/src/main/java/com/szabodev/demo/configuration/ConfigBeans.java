package com.szabodev.demo.configuration;

import com.szabodev.demo.dto.SkillDTO;
import com.szabodev.demo.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class ConfigBeans {

    private static final Logger logger = LoggerFactory.getLogger(ConfigBeans.class);

    @Bean
    public CommandLineRunner commandLineRunner(SkillService skillService, AppConfig appConfig) {
        return args -> {
            if (skillService.findAll().isEmpty()) {
                skillService.save(new SkillDTO("javascript", "Javascript language skill"));
                skillService.save(new SkillDTO("java", "Java language skill"));
                skillService.save(new SkillDTO("html", "HTML skill"));
                skillService.save(new SkillDTO("css", "CSS skill"));
            }
            String appVersion = appConfig.getAppVersion();
            logger.info("Application version: {}", appVersion);
        };
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("en"));
        return slr;
    }
}
