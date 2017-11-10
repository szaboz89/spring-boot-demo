package com.szabodev.demo.service;

import com.szabodev.demo.dao.SkillRepository;
import com.szabodev.demo.dto.SkillDTO;
import com.szabodev.demo.mapper.SkillMapper;
import com.szabodev.demo.model.Skill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkillServiceIT {

    @Autowired
    private SkillService skillService;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SkillMapper skillMapper;

    private static final String NEW_DESCRIPTION = "New Description";

    @Transactional
    @Test
    public void save() throws Exception {
        // given
        Iterable<Skill> skills = skillRepository.findAll();
        Skill testSkill = skills.iterator().next();
        SkillDTO skillDTO = skillMapper.toDTO(testSkill);

        // when
        skillDTO.setDescription(NEW_DESCRIPTION);
        SkillDTO savedSkill = skillService.save(skillDTO);

        // then
        assertEquals(NEW_DESCRIPTION, savedSkill.getDescription());
        assertEquals(testSkill.getId(), savedSkill.getId());
        assertEquals(testSkill.getLabel(), savedSkill.getLabel());
    }

}
