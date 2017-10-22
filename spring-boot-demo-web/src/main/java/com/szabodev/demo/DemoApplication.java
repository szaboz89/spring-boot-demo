package com.szabodev.demo;

import com.szabodev.demo.configuration.AppConfig;
import com.szabodev.demo.dto.SkillDTO;
import com.szabodev.demo.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Configuration
@EnableJpaRepositories
@EnableJpaAuditing
@EntityScan
@ComponentScan
@SpringBootApplication
@EnableScheduling
public class DemoApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    private final SkillService skillService;

    private final AppConfig appConfig;

    @Autowired
    public DemoApplication(SkillService skillService, AppConfig appConfig) {
        this.skillService = skillService;
        this.appConfig = appConfig;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if (skillService.findAll().size() == 0) {
            List<SkillDTO> defaultSkills = new ArrayList<>();
            defaultSkills.add(new SkillDTO("javascript", "Javascript language skill"));
            defaultSkills.add(new SkillDTO("java", "Java language skill"));
            defaultSkills.add(new SkillDTO("html", "HTML skill"));
            defaultSkills.add(new SkillDTO("css", "CSS skill"));
            defaultSkills.forEach(skillService::save);
        }

        String appVersion = appConfig.getAppVersion();
        System.out.println("Application version: " + appVersion);
        logger.info("Application version: " + appVersion);
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("en"));
        return slr;
    }
}
