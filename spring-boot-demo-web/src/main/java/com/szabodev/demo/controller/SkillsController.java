package com.szabodev.demo.controller;

import com.szabodev.demo.dto.SkillDTO;
import com.szabodev.demo.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class SkillsController {

    private static final Logger logger = LoggerFactory.getLogger(SkillsController.class);
    private static final String SKILLS = "developers/skills";
    private static final String REDIRECT_SKILLS = "redirect:/skills";

    private final SkillService skillService;

    @Autowired
    public SkillsController(SkillService skillService) {
        this.skillService = skillService;
    }

    @RequestMapping(value = "/skills", method = RequestMethod.GET)
    public String listSkills(Model model) {
        model.addAttribute("skills", skillService.findAll());
        model.addAttribute("newSkill", new SkillDTO());
        return SKILLS;
    }

    @RequestMapping(value = "/skills", method = RequestMethod.POST)
    public String addSkill(@Valid @ModelAttribute("newSkill") SkillDTO newSkill, BindingResult bindingResult, Model model) {
        logger.debug("addSkill called, newSkill: " + newSkill);
        if (bindingResult.hasErrors()) {
            logger.debug("bindingResult has errors: ");
            bindingResult.getAllErrors().forEach(objectError -> logger.debug(objectError.toString()));
            model.addAttribute("newSkill", newSkill);
        } else {
            skillService.save(newSkill);
            model.addAttribute("newSkill", new SkillDTO());
        }
        model.addAttribute("skills", skillService.findAll());

        return SKILLS;
    }

    @RequestMapping(value = "/skills/{id}/delete", method = RequestMethod.GET)
    public String deleteSkill(@PathVariable Long id) {
        logger.debug("deleteSkill called with id: " + id);
        SkillDTO skill = skillService.findById(id).orElse(null);
        if (skill != null && !skill.isConnected()) {
            skillService.delete(skill);
        }
        return REDIRECT_SKILLS;
    }

}