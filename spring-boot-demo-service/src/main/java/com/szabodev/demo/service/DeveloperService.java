package com.szabodev.demo.service;

import com.szabodev.demo.dto.DeveloperDTO;

import java.util.List;
import java.util.Optional;

public interface DeveloperService {

    List<DeveloperDTO> findAll();

    DeveloperDTO save(DeveloperDTO developerDTO);

    Optional<DeveloperDTO> findById(Long id);

    void delete(DeveloperDTO developer);

    void saveAll(List<DeveloperDTO> developers);

}
