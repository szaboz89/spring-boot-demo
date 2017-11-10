package com.szabodev.demo.controller;

import com.szabodev.demo.dto.DeveloperDTO;
import com.szabodev.demo.dto.SkillDTO;
import com.szabodev.demo.service.DeveloperService;
import com.szabodev.demo.service.SkillService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class DevelopersControllerTest {

    @Mock
    private DeveloperService developerService;

    @Mock
    private SkillService skillService;

    @Mock
    private Model model;

    private DevelopersController developersController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        developersController = new DevelopersController(developerService, skillService);
        mockMvc = MockMvcBuilders.standaloneSetup(developersController).build();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void listDevelopersWithoutFilter() throws Exception {

        // given
        List<DeveloperDTO> developers = new ArrayList<>();
        developers.add(new DeveloperDTO());
        developers.add(new DeveloperDTO());

        // when
        when(developerService.findAll()).thenReturn(developers);
        ArgumentCaptor<List<DeveloperDTO>> argumentCaptor = ArgumentCaptor.forClass(List.class);
        String viewName = developersController.listDevelopers(model, null);

        // then
        assertEquals("developers/developers", viewName);
        verify(developerService, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("developers"), argumentCaptor.capture());
        List<DeveloperDTO> listInController = argumentCaptor.getValue();
        assertEquals(2, listInController.size());
    }

    @Test
    public void addDeveloper() throws Exception {

        // given
        DeveloperDTO developer = new DeveloperDTO();
        developer.setId(1L);
        developer.setFirstName("firstName");
        developer.setLastName("lastName");

        // when
        when(developerService.save(any())).thenReturn(developer);

        // then
        mockMvc.perform(post("/developers")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", "firstName")
                .param("lastName", "lastName")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/developers"));
    }

    @Test
    public void editDeveloper() throws Exception {
    }

    @Test
    public void deleteDeveloper() throws Exception {

        // given
        DeveloperDTO developerDTO = new DeveloperDTO();
        developerDTO.setId(1L);
        Optional<DeveloperDTO> optional = Optional.of(developerDTO);

        // when
        when(developerService.findById(anyLong())).thenReturn(optional);

        // then
        mockMvc.perform(get("/developers/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/developers"));
        verify(developerService, times(1)).delete(any(DeveloperDTO.class));
    }

    @Test
    public void viewDeveloper() throws Exception {
    }

    @Test
    public void addSkillToDeveloper() throws Exception {

        // given
        DeveloperDTO developerDTO = new DeveloperDTO();
        developerDTO.setId(1L);
        Optional<DeveloperDTO> developerOptional = Optional.of(developerDTO);
        SkillDTO skillDTO = new SkillDTO();
        skillDTO.setId(1L);
        Optional<SkillDTO> skillOptional = Optional.of(skillDTO);

        // when
        when(developerService.findById(1L)).thenReturn(developerOptional);
        when(skillService.findById(1L)).thenReturn(skillOptional);

        // then
        mockMvc.perform(post("/developers/1/skills")
                .param("skillId", "1")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/developers/1"));
    }

    @Test
    public void removeSkillFromDeveloper() throws Exception {
        // given
        SkillDTO skillDTO = new SkillDTO();
        skillDTO.setId(1L);
        Optional<SkillDTO> skillOptional = Optional.of(skillDTO);
        List<SkillDTO> skills = new ArrayList<>();
        skills.add(skillDTO);
        DeveloperDTO developerDTO = new DeveloperDTO();
        developerDTO.setId(1L);
        developerDTO.setSkills(skills);
        Optional<DeveloperDTO> developerOptional = Optional.of(developerDTO);

        // when
        when(developerService.findById(1L)).thenReturn(developerOptional);
        when(skillService.findById(1L)).thenReturn(skillOptional);

        // then
        mockMvc.perform(get("/developers/1/skills/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/developers/1"));
        verify(developerService, times(1)).save(any(DeveloperDTO.class));
    }

}