package edu.lethe.gameslib.model.dto;

import jakarta.validation.constraints.NotBlank;

public class PublisherDTO {
    private Long id;
    @NotBlank
    private String name;

    public PublisherDTO(){}

    public PublisherDTO(String name) {
        this.name = name;
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
}
