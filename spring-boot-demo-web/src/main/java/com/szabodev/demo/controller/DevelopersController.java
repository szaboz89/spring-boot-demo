package com.szabodev.demo.controller;

import com.szabodev.demo.dao.DeveloperFilter;
import com.szabodev.demo.dto.DeveloperDTO;
import com.szabodev.demo.dto.SkillDTO;
import com.szabodev.demo.service.DeveloperService;
import com.szabodev.demo.service.SkillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class DevelopersController {

    private static final Logger logger = LoggerFactory.getLogger(DevelopersController.class);

    private static final String REDIRECT_DEVELOPERS = "redirect:/developers";
    private static final String REDIRECT_DEVELOPERS_ID = "redirect:/developers/";
    private static final String DEVELOPERS = "developers/developers";
    private static final String DEVELOPER_VIEW = "developers/developer";

    private static final String DEVELOPER_LEVEL_VALUES = "developerLevelValues";
    private static final String EDITED_DEVELOPER = "editedDeveloper";
    private static final String DEVELOPER_FILTER = "developerFilter";
    private static final String DEVELOPERS_ATTR = "developers";

    private final DeveloperService developerService;
    private final SkillService skillService;

    @Autowired
    public DevelopersController(DeveloperService developerService, SkillService skillService) {
        this.developerService = developerService;
        this.skillService = skillService;
    }

    @RequestMapping(value = "/developers", method = RequestMethod.GET)
    public String listDevelopers(Model model, @ModelAttribute(DEVELOPER_FILTER) DeveloperFilter developerFilter) {
        logger.debug("listDevelopers called, developerFilter: {}", developerFilter);
        if (developerFilter != null) {
            model.addAttribute(DEVELOPER_FILTER, developerFilter);
            model.addAttribute(DEVELOPERS_ATTR, developerService.findByDeveloperCriteria(developerFilter));
        } else {
            model.addAttribute(DEVELOPERS_ATTR, developerService.findAll());
            model.addAttribute(DEVELOPER_FILTER, new DeveloperFilter());
        }
        model.addAttribute(EDITED_DEVELOPER, new DeveloperDTO());
        model.addAttribute(DEVELOPER_LEVEL_VALUES, DeveloperService.developerLevelValues());
        return DEVELOPERS;
    }

    @RequestMapping(value = "/developers", method = RequestMethod.POST)
    public String addDeveloper(@Valid @ModelAttribute(EDITED_DEVELOPER) DeveloperDTO newDeveloper, BindingResult bindingResult, Model model) {
        logger.debug("addDeveloper called, newDeveloper: {}", newDeveloper);
        if (bindingResult.hasErrors()) {
            logger.debug("bindingResult has errors: ");
            bindingResult.getAllErrors().forEach(objectError -> logger.debug(objectError.toString()));
            model.addAttribute(DEVELOPERS_ATTR, developerService.findAll());
            model.addAttribute(DEVELOPER_FILTER, new DeveloperFilter());
            model.addAttribute(EDITED_DEVELOPER, newDeveloper);
            model.addAttribute(DEVELOPER_LEVEL_VALUES, DeveloperService.developerLevelValues());
            return DEVELOPERS;
        }
        if (newDeveloper.getId() != null) {
            developerService.findById(newDeveloper.getId()).ifPresent(developerDTO -> newDeveloper.setSkills(developerDTO.getSkills()));
        }
        developerService.save(newDeveloper);
        return REDIRECT_DEVELOPERS;
    }

    @RequestMapping("/developers/{id}/edit")
    public String editDeveloper(@PathVariable Long id, Model model) {
        logger.debug("editDeveloper called with id: {}", id);
        DeveloperDTO developer = developerService.findById(id).orElse(null);
        if (developer != null) {
            model.addAttribute(DEVELOPER_FILTER, new DeveloperFilter());
            model.addAttribute(DEVELOPERS_ATTR, developerService.findAll());
            model.addAttribute(EDITED_DEVELOPER, developer);
            model.addAttribute(DEVELOPER_LEVEL_VALUES, DeveloperService.developerLevelValues());
            return DEVELOPERS;
        } else {
            return REDIRECT_DEVELOPERS;
        }
    }

    @RequestMapping(value = "/developers/{id}/delete", method = RequestMethod.GET)
    public String deleteDeveloper(@PathVariable Long id, Model model) {
        logger.debug("deleteDeveloper called with id: {}", id);
        Optional<DeveloperDTO> developer = developerService.findById(id);
        developer.ifPresent(developerService::delete);
        return REDIRECT_DEVELOPERS;
    }

    @RequestMapping("/developers/{id}")
    public String viewDeveloper(@PathVariable Long id, Model model) {
        logger.debug("viewDeveloper called with id: {}", id);
        DeveloperDTO developer = developerService.findById(id).orElse(null);
        if (developer != null) {
            List<SkillDTO> skills = developer.getSkills();
            List<SkillDTO> available = skillService.findAll();
            available.removeAll(skills);
            model.addAttribute("developer", developer);
            model.addAttribute("skills", available);
            return DEVELOPER_VIEW;
        } else {
            return REDIRECT_DEVELOPERS;
        }
    }

    @RequestMapping(value = "/developers/{id}/skills", method = RequestMethod.POST)
    public String addSkillToDeveloper(@PathVariable Long id, @RequestParam Long skillId, Model model) {
        logger.debug("addSkill called, developerId/skillId: {}/{}", id, skillId);
        Optional<DeveloperDTO> developer = developerService.findById(id);
        if (!developer.isPresent()) {
            return REDIRECT_DEVELOPERS;
        } else if (skillId == null) {
            return REDIRECT_DEVELOPERS_ID + developer.get().getId();
        }
        Optional<SkillDTO> skill = skillService.findById(skillId);
        if (skill.isPresent() && !developer.get().hasSkill(skill.get())) {
            developer.get().getSkills().add(skill.get());
            developerService.save(developer.get());
        }
        return REDIRECT_DEVELOPERS_ID + developer.get().getId();
    }

    @RequestMapping(value = "/developers/{id}/skills/{skillId}", method = RequestMethod.GET)
    public String removeSkillFromDeveloper(@PathVariable Long id, @PathVariable Long skillId, Model model) {
        logger.debug("removeSkill called, developerId/skillId: {}/{}", id, skillId);
        SkillDTO skill = skillService.findById(skillId).orElse(null);
        DeveloperDTO developer = developerService.findById(id).orElse(null);
        if (skill != null && developer != null) {
            developer.getSkills().remove(skill);
            developerService.save(developer);
            model.addAttribute("developer", developerService.findById(id));
            model.addAttribute("skills", skillService.findAll());
            return REDIRECT_DEVELOPERS_ID + developer.getId();
        }
        return REDIRECT_DEVELOPERS;
    }
}
