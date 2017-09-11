package com.szabodev.demo.dto;

import org.springframework.data.annotation.Id;

public class SkillDTO {

    @Id
    private Long id;
    private String label;
    private String description;

    public SkillDTO() {
    }

    public SkillDTO(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkillDTO skillDTO = (SkillDTO) o;

        if (id != null ? !id.equals(skillDTO.id) : skillDTO.id != null) return false;
        if (label != null ? !label.equals(skillDTO.label) : skillDTO.label != null) return false;
        return description != null ? description.equals(skillDTO.description) : skillDTO.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
