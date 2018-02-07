package com.szabodev.demo.mapper;

import com.szabodev.demo.dto.DeveloperDTO;
import com.szabodev.demo.model.Developer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeveloperMapper {

    DeveloperDTO toDTO(Developer developer);

    Developer toEntity(DeveloperDTO developerDTO);

    List<DeveloperDTO> toDTOs(List<Developer> developers);

    List<Developer> toEntityList(List<DeveloperDTO> developerDTOS);
}
