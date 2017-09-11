package com.szabodev.demo.mapper;

import com.szabodev.demo.dto.DeveloperDTO;
import com.szabodev.demo.model.Developer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DeveloperMapper {

    DeveloperMapper INSTANCE = Mappers.getMapper(DeveloperMapper.class);

    DeveloperDTO toDTO(Developer developer);

    Developer toEntity(DeveloperDTO developerDTO);

    List<DeveloperDTO> toDTOs(List<Developer> developers);

    List<Developer> toEntityList(List<DeveloperDTO> developerDTOS);
}
