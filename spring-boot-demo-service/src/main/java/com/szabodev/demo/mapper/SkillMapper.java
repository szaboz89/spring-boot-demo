package com.szabodev.demo.mapper;

import com.szabodev.demo.dto.SkillDTO;
import com.szabodev.demo.model.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SkillMapper {

    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    SkillDTO toDTO(Skill skill);

    Skill toEntity(SkillDTO skillDTO);

    List<SkillDTO> toDTOs(List<Skill> skills);

    List<Skill> toEntities(List<SkillDTO> skills);
}
