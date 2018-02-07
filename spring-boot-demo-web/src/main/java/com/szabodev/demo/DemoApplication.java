package com.szabodev.demo;

import com.szabodev.demo.configuration.AppConfig;
import com.szabodev.demo.dto.SkillDTO;
import com.szabodev.demo.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.Locale;

@Configuration
@EnableJpaRepositories
@EnableJpaAuditing
@EntityScan
@ComponentScan
@SpringBootApplication
@EnableScheduling
public class DemoApplication {

    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

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
