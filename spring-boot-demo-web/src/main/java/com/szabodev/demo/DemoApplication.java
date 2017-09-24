package com.szabodev.demo;

import com.szabodev.demo.dto.SkillDTO;
import com.szabodev.demo.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
@EnableJpaRepositories
@EntityScan
@ComponentScan
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final SkillService skillService;

    @Autowired
    public DemoApplication(SkillService skillService) {
        this.skillService = skillService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if (skillService.findAll().size() == 0) {
            SkillDTO javascript = new SkillDTO("javascript", "Javascript language skill");
            SkillDTO java = new SkillDTO("java", "Java language skill");
            SkillDTO html = new SkillDTO("html", "HTML skill");
            SkillDTO css = new SkillDTO("css", "CSS skill");

            skillService.save(javascript);
            skillService.save(java);
            skillService.save(html);
            skillService.save(css);
        }

    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("en"));
        return slr;
    }
}
