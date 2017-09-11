package com.szabodev.demo.service;

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

    @Autowired
    public DeveloperServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public List<DeveloperDTO> findAll() {
        return DeveloperMapper.INSTANCE.toDTOs((List<Developer>) developerRepository.findAll());
    }

    @Override
    public DeveloperDTO save(DeveloperDTO developerDTO) {
        Developer developer = DeveloperMapper.INSTANCE.toEntity(developerDTO);
        developerRepository.save(developer);
        developerDTO.setId(developer.getId());
        return developerDTO;
    }

    @Override
    public Optional<DeveloperDTO> findById(Long id) {
        Optional<Developer> byId = developerRepository.findById(id);
        return Optional.ofNullable(DeveloperMapper.INSTANCE.toDTO(byId.orElse(null)));
    }

    @Override
    public void delete(DeveloperDTO developer) {
        developerRepository.delete(DeveloperMapper.INSTANCE.toEntity(developer));
    }

    @Override
    public void saveAll(List<DeveloperDTO> developers) {
        developerRepository.saveAll(DeveloperMapper.INSTANCE.toEntityList(developers));
    }
}
