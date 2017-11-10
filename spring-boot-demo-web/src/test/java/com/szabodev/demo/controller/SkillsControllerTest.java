package com.szabodev.demo.controller;

import com.szabodev.demo.dto.SkillDTO;
import com.szabodev.demo.service.SkillService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class SkillsControllerTest {

    @Mock
    private SkillService skillService;

    @Mock
    private Model model;

    private SkillsController skillsController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        skillsController = new SkillsController(skillService);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(skillsController).build();

        mockMvc.perform(get("/skills"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("developers/skills"));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void listSkills() throws Exception {

        // given
        List<SkillDTO> skills = new ArrayList<>();
        skills.add(new SkillDTO());
        skills.add(new SkillDTO());

        // when
        when(skillService.findAll()).thenReturn(skills);
        ArgumentCaptor<List<SkillDTO>> argumentCaptor = ArgumentCaptor.forClass(List.class);
        String viewName = skillsController.listSkills(model);

        // then
        assertEquals("developers/skills", viewName);
        verify(skillService, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("skills"), argumentCaptor.capture());
        List<SkillDTO> listInController = argumentCaptor.getValue();
        assertEquals(2, listInController.size());

    }

}