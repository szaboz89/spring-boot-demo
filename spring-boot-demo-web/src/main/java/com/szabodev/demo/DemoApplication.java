package com.szabodev.demo;

import com.szabodev.demo.model.Developer;
import com.szabodev.demo.model.Skill;
import com.szabodev.demo.dao.DeveloperRepository;
import com.szabodev.demo.dao.SkillRepository;
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

    private final DeveloperRepository developerRepository;

    private final SkillRepository skillRepository;

    @Autowired
    public DemoApplication(DeveloperRepository developerRepository, SkillRepository skillRepository) {
        this.developerRepository = developerRepository;
        this.skillRepository = skillRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Skill javascript = new Skill("javascript", "Javascript language skill");
        Skill java = new Skill("java", "Java language skill");
        Skill html = new Skill("html", "HTML skill");
        Skill css = new Skill("css", "CSS skill");

        skillRepository.save(javascript);
        skillRepository.save(java);
        skillRepository.save(html);
        skillRepository.save(css);

        List<Developer> developers = new LinkedList<>();
        developers.add(new Developer("Mark", "Taylor", "mark.taylor@example.com", Arrays.asList(javascript, java, html)));
        developerRepository.saveAll(developers);
    }
}
