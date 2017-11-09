package com.szabodev.demo.dto;

import org.hibernate.validator.constraints.NotBlank;

public class SkillDTO extends AuditDataDTO {

    private Long id;

    @NotBlank
    private String label;

    private String description;

    private boolean connected;

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

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkillDTO skillDTO = (SkillDTO) o;

        return id.equals(skillDTO.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "SkillDTO{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", connected=" + connected +
                "} " + super.toString();
    }
}
