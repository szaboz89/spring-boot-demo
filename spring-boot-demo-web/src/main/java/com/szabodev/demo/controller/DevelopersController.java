package com.szabodev.demo.controller;

import com.szabodev.demo.dto.DeveloperDTO;
import com.szabodev.demo.dto.SkillDTO;
import com.szabodev.demo.service.DeveloperService;
import com.szabodev.demo.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DevelopersController {

    private static final Logger logger = LoggerFactory.getLogger(DevelopersController.class);

    private final DeveloperService developerService;
    private final SkillService skillService;

    @Autowired
    public DevelopersController(DeveloperService developerService, SkillService skillService) {
        this.developerService = developerService;
        this.skillService = skillService;
    }

    @RequestMapping(value = "/developers", method = RequestMethod.GET)
    public String listDevelopers(Model model) {
        logger.debug("listDevelopers called");
        model.addAttribute("developers", developerService.findAll());
        model.addAttribute("editedDeveloper", new DeveloperDTO());
        return "developers/developers";
    }

    @RequestMapping(value = "/developers", method = RequestMethod.POST)
    public String addDeveloper(@ModelAttribute("editedDeveloper") DeveloperDTO newDeveloper, Model model) {
        logger.debug("addDeveloper called");
        if (newDeveloper.getId() != null) {
            DeveloperDTO developerDTO = developerService.findById(newDeveloper.getId()).orElse(null);
            if (developerDTO != null) {
                newDeveloper.setSkills(developerDTO.getSkills());
            }
        }
        developerService.save(newDeveloper);
        model.addAttribute("developers", developerService.findAll());
        model.addAttribute("editedDeveloper", new DeveloperDTO());
        return "developers/developers";
    }

    @RequestMapping("/developers/{id}/edit")
    public String editDeveloper(@PathVariable Long id, Model model) {
        logger.debug("editDeveloper called");
        DeveloperDTO developer = developerService.findById(id).orElse(null);
        if (developer != null) {
            model.addAttribute("developers", developerService.findAll());
            model.addAttribute("editedDeveloper", developer);
            return "developers/developers";
        } else {
            return "redirect:/developers";
        }
    }

    @RequestMapping(value = "/developers/{id}/delete", method = RequestMethod.GET)
    public String deleteDeveloper(@PathVariable Long id, Model model) {
        logger.debug("deleteDeveloper called");
        DeveloperDTO developer = developerService.findById(id).orElse(null);
        if (developer != null) {
            developerService.delete(developer);
        }
        return "redirect:/developers";
    }

    @RequestMapping("/developers/{id}")
    public String viewDeveloper(@PathVariable Long id, Model model) {
        logger.debug("viewDeveloper called");
        DeveloperDTO developer = developerService.findById(id).orElse(null);
        if (developer != null) {
            model.addAttribute("developer", developer);
            model.addAttribute("skills", skillService.findAll());
            return "developers/developer";
        } else {
            return "redirect:/developers";
        }
    }

    @RequestMapping(value = "/developers/{id}/skills", method = RequestMethod.POST)
    public String addSkill(@PathVariable Long id, @RequestParam Long skillId, Model model) {
        logger.debug("addSkill called");
        SkillDTO skill = skillService.findById(skillId).orElse(null);
        DeveloperDTO developer = developerService.findById(id).orElse(null);

        if (developer != null) {
            if (!developer.hasSkill(skill)) {
                developer.getSkills().add(skill);
            }
            developerService.save(developer);
            model.addAttribute("developer", developerService.findById(id));
            model.addAttribute("skills", skillService.findAll());
            return "redirect:/developers/" + developer.getId();
        }

        model.addAttribute("developers", developerService.findAll());
        return "redirect:/developers";
    }

    @RequestMapping(value = "/developers/{id}/skills/{skillId}", method = RequestMethod.GET)
    public String removeSkill(@PathVariable Long id, @PathVariable Long skillId, Model model) {
        logger.debug("removeSkill called");
        SkillDTO skill = skillService.findById(skillId).orElse(null);
        DeveloperDTO developer = developerService.findById(id).orElse(null);
        if (skill != null && developer != null) {
            developer.getSkills().remove(skill);
            developerService.save(developer);
            model.addAttribute("developer", developerService.findById(id));
            model.addAttribute("skills", skillService.findAll());
            return "redirect:/developers/" + developer.getId();
        }
        model.addAttribute("developers", developerService.findAll());
        return "redirect:/developers";
    }

}
