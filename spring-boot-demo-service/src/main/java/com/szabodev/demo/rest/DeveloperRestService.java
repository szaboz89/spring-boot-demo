package com.szabodev.demo.rest;

import com.szabodev.demo.dto.DeveloperDTO;
import com.szabodev.demo.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class DeveloperRestService {

    private final DeveloperService developerService;

    @Autowired
    public DeveloperRestService(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping("/developers")
    public List<DeveloperDTO> getDevelopers() {
        return developerService.findAll();
    }

    @GetMapping("/developers/{id}")
    public DeveloperDTO getDeveloper(@PathVariable Long id) {
        return developerService.findById(id).orElse(null);
    }
}
