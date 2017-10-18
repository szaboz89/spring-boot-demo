package com.szabodev.demo.dto;

import com.szabodev.demo.model.DeveloperLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeveloperDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private DeveloperDetailDTO developerDetail = new DeveloperDetailDTO();
    private DeveloperLevel developerLevel;
    private List<SkillDTO> skills;

    public DeveloperDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DeveloperDetailDTO getDeveloperDetail() {
        return developerDetail;
    }

    public void setDeveloperDetail(DeveloperDetailDTO developerDetail) {
        this.developerDetail = developerDetail;
    }

    public DeveloperLevel getDeveloperLevel() {
        return developerLevel;
    }

    public void setDeveloperLevel(DeveloperLevel developerLevel) {
        this.developerLevel = developerLevel;
    }

    public List<SkillDTO> getSkills() {
        if (skills == null) {
            skills = new ArrayList<>();
        }
        return skills;
    }

    public void setSkills(List<SkillDTO> skills) {
        this.skills = skills;
    }

    public boolean hasSkill(SkillDTO skill) {
        for (SkillDTO containedSkill : getSkills()) {
            if (Objects.equals(containedSkill.getId(), skill.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "DeveloperDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", developerDetail=" + developerDetail +
                ", developerLevel=" + developerLevel +
                ", skills=" + skills +
                '}';
    }
}
