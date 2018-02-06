package com.szabodev.demo.service;

import com.szabodev.demo.dao.DeveloperFilter;
import com.szabodev.demo.dto.DeveloperDTO;
import com.szabodev.demo.model.DeveloperLevel;

import java.util.List;
import java.util.Optional;

public interface DeveloperService {

    static List<String> developerLevelValues() {
        return DeveloperLevel.stringValues();
    }

    List<DeveloperDTO> findAll();

    List<DeveloperDTO> findByDeveloperCriteria(DeveloperFilter developerFilter);

    DeveloperDTO save(DeveloperDTO developerDTO);

    Optional<DeveloperDTO> findById(Long id);

    void delete(DeveloperDTO developer);

    void saveAll(List<DeveloperDTO> developers);

}
