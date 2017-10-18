package com.szabodev.demo.dto;

public class DeveloperDetailDTO {

    private Long id;

    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "DeveloperDetailDTO{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
