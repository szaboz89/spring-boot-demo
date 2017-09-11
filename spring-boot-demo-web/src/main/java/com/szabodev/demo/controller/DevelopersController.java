package com.szabodev.demo.controller;

import com.szabodev.demo.model.Developer;
import com.szabodev.demo.model.Skill;
import com.szabodev.demo.dao.DeveloperRepository;
import com.szabodev.demo.dao.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import sun.misc.Request;

@Controller
public class DevelopersController {

    private final DeveloperRepository repository;
    private final SkillRepository skillRepository;

    @Autowired
    public DevelopersController(DeveloperRepository repository, SkillRepository skillRepository) {
        this.repository = repository;
        this.skillRepository = skillRepository;
    }

    @RequestMapping(value = "/developers", method = RequestMethod.GET)
    public String listDevelopers(Model model) {
        model.addAttribute("developers", repository.findAll());
        model.addAttribute("editedDeveloper", new Developer());
        return "developers/developers";
    }

    @RequestMapping(value = "/developers", method = RequestMethod.POST)
    public String addDeveloper(@ModelAttribute("editedDeveloper") Developer newDeveloper, Model model) {
        repository.save(newDeveloper);
        model.addAttribute("developers", repository.findAll());
        model.addAttribute("editedDeveloper", new Developer());
        return "developers/developers";
    }

    @RequestMapping("/developers/{id}/edit")
    public String editDeveloper(@PathVariable Long id, Model model) {
        Developer developer = repository.findById(id).orElse(null);
        if (developer != null) {
            model.addAttribute("developers", repository.findAll());
            model.addAttribute("editedDeveloper", developer);
            return "developers/developers";
        } else {
            return "redirect:/developers";
        }
    }

    @RequestMapping(value = "/developers/{id}/delete", method = RequestMethod.GET)
    public String deleteDeveloper(@PathVariable Long id, Model model) {
        Developer developer = repository.findById(id).orElse(null);
        if (developer != null) {
            repository.delete(developer);
        }
        return "redirect:/developers";
    }

    @RequestMapping("/developers/{id}")
    public String viewDeveloper(@PathVariable Long id, Model model) {
        Developer developer = repository.findById(id).orElse(null);
        if (developer != null) {
            model.addAttribute("developer", developer);
            model.addAttribute("skills", skillRepository.findAll());
            return "developers/developer";
        } else {
            return "redirect:/developers";
        }
    }

    @RequestMapping(value = "/developers/{id}/skills", method = RequestMethod.POST)
    public String addSkill(@PathVariable Long id, @RequestParam Long skillId, Model model) {
        Skill skill = skillRepository.findById(skillId).orElse(null);
        Developer developer = repository.findById(id).orElse(null);

        if (developer != null) {
            if (!developer.hasSkill(skill)) {
                developer.getSkills().add(skill);
            }
            repository.save(developer);
            model.addAttribute("developer", repository.findById(id));
            model.addAttribute("skills", skillRepository.findAll());
            return "redirect:/developers/" + developer.getId();
        }

        model.addAttribute("developers", repository.findAll());
        return "redirect:/developers";
    }

    @RequestMapping(value = "/developers/{id}/skills/{skillId}", method = RequestMethod.GET)
    public String removeSkill(@PathVariable Long id, @PathVariable Long skillId, Model model) {
        Skill skill = skillRepository.findById(skillId).orElse(null);
        Developer developer = repository.findById(id).orElse(null);
        if (skill != null && developer != null) {
            developer.getSkills().remove(skill);
            repository.save(developer);
            model.addAttribute("developer", repository.findById(id));
            model.addAttribute("skills", skillRepository.findAll());
            return "redirect:/developers/" + developer.getId();
        }
        model.addAttribute("developers", repository.findAll());
        return "redirect:/developers";
    }

}
