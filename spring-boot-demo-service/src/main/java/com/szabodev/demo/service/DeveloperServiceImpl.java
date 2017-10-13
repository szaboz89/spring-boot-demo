package com.szabodev.demo.service;

import com.szabodev.demo.dao.DeveloperCustomRepository;
import com.szabodev.demo.dao.DeveloperFilter;
import com.szabodev.demo.dao.DeveloperRepository;
import com.szabodev.demo.dto.DeveloperDTO;
import com.szabodev.demo.mapper.DeveloperMapper;
import com.szabodev.demo.model.Developer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    private final DeveloperCustomRepository developerCustomRepository;

    private final DeveloperMapper developerMapper;

    @Autowired
    public DeveloperServiceImpl(DeveloperRepository developerRepository, DeveloperCustomRepository developerCustomRepository, DeveloperMapper developerMapper) {
        this.developerRepository = developerRepository;
        this.developerCustomRepository = developerCustomRepository;
        this.developerMapper = developerMapper;
    }

    @Override
    public List<DeveloperDTO> findAll() {
        return developerMapper.toDTOs((List<Developer>) developerRepository.findAll());
    }

    @Override
    public List<DeveloperDTO> findByDeveloperCriteria(DeveloperFilter developerFilter) {
        return developerMapper.toDTOs(developerCustomRepository.findByDeveloperCriteria(developerFilter));
    }

    @Override
    public DeveloperDTO save(DeveloperDTO developerDTO) {
        Developer developer = developerMapper.toEntity(developerDTO);
        developerRepository.save(developer);
        developerDTO.setId(developer.getId());
        return developerDTO;
    }

    @Override
    public Optional<DeveloperDTO> findById(Long id) {
        Optional<Developer> byId = developerRepository.findById(id);
        return Optional.ofNullable(developerMapper.toDTO(byId.orElse(null)));
    }

    @Override
    public void delete(DeveloperDTO developer) {
        developerRepository.delete(developerMapper.toEntity(developer));
    }

    @Override
    public void saveAll(List<DeveloperDTO> developers) {
        developerRepository.saveAll(developerMapper.toEntityList(developers));
    }
}
