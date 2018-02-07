package com.szabodev.demo.service;

import com.szabodev.demo.dao.SkillRepository;
import com.szabodev.demo.dto.SkillDTO;
import com.szabodev.demo.mapper.SkillMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class SkillServiceImplTest {

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private SkillMapper skillMapper;

    private SkillServiceImpl skillService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        skillService = new SkillServiceImpl(skillRepository, skillMapper);
    }

    @Test
    public void findAll() {
        // given
        List<SkillDTO> skillsData = new ArrayList<>();
        skillsData.add(new SkillDTO());
        when(skillService.findAll()).thenReturn(skillsData);

        // when
        List<SkillDTO> skills = skillService.findAll();

        // then
        assertEquals(1, skills.size());
    }

}
