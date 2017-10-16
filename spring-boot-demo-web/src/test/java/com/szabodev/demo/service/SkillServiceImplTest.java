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
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        skillService = new SkillServiceImpl(skillRepository, skillMapper);
    }

    @Test
    public void findAll() throws Exception {
        List<SkillDTO> skillsData = new ArrayList<>();
        skillsData.add(new SkillDTO());
        when(skillService.findAll()).thenReturn(skillsData);

        List<SkillDTO> skills = skillService.findAll();

        assertEquals(skills.size(), 1);
        // verify(skillRepository, times(1)).findAll();
    }

}