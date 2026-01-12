package com.legar.auweb.dto;

import com.legar.auweb.entity.InputOutput;

public class InputOutputDto {
    private Long id;
    private String name;
    private String uri;

    public InputOutputDto(Long id, String name, String uri) {
        this.id = id;
        this.name = name;
        this.uri = uri;
    }

    public static InputOutputDto fromEntity(InputOutput entity) {
        if (entity == null) return null;
        return new InputOutputDto(
                entity.getId(),
                entity.getName(),
                entity.getUri()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}