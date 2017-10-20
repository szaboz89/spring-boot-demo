package com.szabodev.demo.service;

import com.szabodev.demo.dao.SkillRepository;
import com.szabodev.demo.dto.SkillDTO;
import com.szabodev.demo.mapper.SkillMapper;
import com.szabodev.demo.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    private final SkillMapper skillMapper;

    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository, SkillMapper skillMapper) {
        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
    }

    @Override
    public List<SkillDTO> findAll() {
        return skillMapper.toDTOs((List<Skill>) skillRepository.findAll());
    }

    @Override
    public Optional<SkillDTO> findById(Long skillId) {
        Optional<Skill> byId = skillRepository.findById(skillId);
        return Optional.ofNullable(skillMapper.toDTO(byId.orElse(null)));
    }

    @Override
    public SkillDTO save(SkillDTO skillDTO) {
        Skill skill = skillMapper.toEntity(skillDTO);

        skillRepository.save(skill);
        skillDTO.setId(skill.getId());
        return skillDTO;
    }

    @Override
    public void delete(SkillDTO skill) {
        skillRepository.delete(skillMapper.toEntity(skill));
    }
}
