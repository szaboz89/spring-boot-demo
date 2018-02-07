package com.szabodev.demo.mapper;

import com.szabodev.demo.dto.SkillDTO;
import com.szabodev.demo.model.Skill;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    SkillDTO toDTO(Skill skill);

    Skill toEntity(SkillDTO skillDTO);

    List<SkillDTO> toDTOs(List<Skill> skills);

    List<Skill> toEntities(List<SkillDTO> skills);
}
