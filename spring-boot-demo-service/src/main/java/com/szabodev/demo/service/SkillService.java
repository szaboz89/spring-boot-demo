package com.szabodev.demo.service;

import com.szabodev.demo.dto.SkillDTO;

import java.util.List;
import java.util.Optional;

public interface SkillService {

    List<SkillDTO> findAll();

    Optional<SkillDTO> findById(Long skillId);

    SkillDTO save(SkillDTO skillDTO);

    void delete(SkillDTO skill);
}
