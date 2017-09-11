package com.szabodev.demo;

import com.szabodev.demo.dto.DeveloperDTO;
import com.szabodev.demo.dto.SkillDTO;
import com.szabodev.demo.service.DeveloperService;
import com.szabodev.demo.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@EnableJpaRepositories
@EntityScan
@ComponentScan
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final DeveloperService developerService;
    private final SkillService skillService;

    @Autowired
    public DemoApplication(DeveloperService developerService, SkillService skillService) {
        this.developerService = developerService;
        this.skillService = skillService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        SkillDTO javascript = new SkillDTO("javascript", "Javascript language skill");
        SkillDTO java = new SkillDTO("java", "Java language skill");
        SkillDTO html = new SkillDTO("html", "HTML skill");
        SkillDTO css = new SkillDTO("css", "CSS skill");

        javascript = skillService.save(javascript);
        java = skillService.save(java);
        html = skillService.save(html);
        css = skillService.save(css);

        List<DeveloperDTO> developers = new LinkedList<>();
        developers.add(new DeveloperDTO("Mark", "Taylor", "mark.taylor@example.com", Arrays.asList(javascript, java, html)));
        developerService.saveAll(developers);
    }
}
