package com.szabodev.demo.controller;

import com.szabodev.demo.model.Developer;
import com.szabodev.demo.model.Skill;
import com.szabodev.demo.dao.DeveloperRepository;
import com.szabodev.demo.dao.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

@Controller
public class DevelopersController {

    private final DeveloperRepository repository;
    private final SkillRepository skillRepository;

    @Autowired
    public DevelopersController(DeveloperRepository repository, SkillRepository skillRepository) {
        this.repository = repository;
        this.skillRepository = skillRepository;
    }

    @RequestMapping("/developers/{id}")
    public String developer(@PathVariable Long id, Model model) {
        Developer developer = repository.findById(id).orElse(null);
        if (developer != null) {
            model.addAttribute("developer", developer);
            model.addAttribute("skills", skillRepository.findAll());
            return "developers/developer";
        } else {
            return "redirect:/developers";
        }

    }

    @RequestMapping(value = "/developers", method = RequestMethod.GET)
    public String developersList(Model model) {
        model.addAttribute("developers", repository.findAll());
        return "developers/developers";
    }

    @RequestMapping(value = "/developers", method = RequestMethod.POST)
    public String developersAdd(@RequestParam String email, @RequestParam String firstName, @RequestParam String lastName, Model model) {
        Developer newDeveloper = new Developer();
        newDeveloper.setEmail(email);
        newDeveloper.setFirstName(firstName);
        newDeveloper.setLastName(lastName);
        repository.save(newDeveloper);

        model.addAttribute("developer", newDeveloper);
        model.addAttribute("skills", skillRepository.findAll());
        return "redirect:/developers/" + newDeveloper.getId();
    }

    @RequestMapping(value = "/developers/{id}/skills", method = RequestMethod.POST)
    public String developersAddSkill(@PathVariable Long id, @RequestParam Long skillId, Model model) {
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

}
