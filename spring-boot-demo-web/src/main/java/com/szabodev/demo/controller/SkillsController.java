package com.szabodev.demo.controller;

import com.szabodev.demo.dto.SkillDTO;
import com.szabodev.demo.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SkillsController {

    private final SkillService skillService;

    @Autowired
    public SkillsController(SkillService skillService) {
        this.skillService = skillService;
    }

    @RequestMapping(value = "/skills", method = RequestMethod.GET)
    public String listSkills(Model model) {
        model.addAttribute("skills", skillService.findAll());
        model.addAttribute("newSkill", new SkillDTO());
        return "developers/skills";
    }

    @RequestMapping(value = "/skills", method = RequestMethod.POST)
    public String addSkill(@ModelAttribute("newSkill") SkillDTO newSkill, Model model) {
        skillService.save(newSkill);
        model.addAttribute("skills", skillService.findAll());
        model.addAttribute("newSkill", new SkillDTO());
        return "developers/skills";
    }

    @RequestMapping(value = "/skills/{id}/delete", method = RequestMethod.GET)
    public String deleteSkill(@PathVariable Long id, Model model) {
        SkillDTO skill = skillService.findById(id).orElse(null);
        if (skill != null) {
            skillService.delete(skill);
        }
        return "redirect:/skills";
    }

}
