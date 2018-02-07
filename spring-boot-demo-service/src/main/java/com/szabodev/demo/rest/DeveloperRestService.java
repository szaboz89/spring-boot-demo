package com.szabodev.demo.rest;

import com.szabodev.demo.dto.DeveloperDTO;
import com.szabodev.demo.dto.SkillDTO;
import com.szabodev.demo.service.DeveloperService;
import com.szabodev.demo.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class DeveloperRestService {

    private static final Logger logger = LoggerFactory.getLogger(DeveloperRestService.class);

    private final DeveloperService developerService;

    private final SkillService skillService;

    @Autowired
    public DeveloperRestService(DeveloperService developerService, SkillService skillService) {
        this.developerService = developerService;
        this.skillService = skillService;
    }

    @GetMapping("/skills")
    public List<SkillDTO> getSkills() {
        logger.debug("getDevelopers");
        return skillService.findAll();
    }

    @GetMapping("/developers")
    public List<DeveloperDTO> getDevelopers() {
        logger.debug("getDevelopers");
        return developerService.findAll();
    }

    @GetMapping("/developers/{id}")
    public DeveloperDTO getDeveloper(@PathVariable Long id) {
        logger.debug("getDeveloperById called with id: {}", id);
        return developerService.findById(id).orElse(null);
    }

    @GetMapping("/developers/{id}/skills")
    public List<SkillDTO> getDeveloperSkills(@PathVariable Long id) {
        logger.debug("getDeveloperSkills called with id: {}", id);
        Optional<DeveloperDTO> byId = developerService.findById(id);
        if (byId.isPresent()) {
            return byId.get().getSkills();
        } else {
            return new ArrayList<>();
        }
    }
}
