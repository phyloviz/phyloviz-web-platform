package org.phyloviz.pwp.uploader.service.dtos.createProject;

public class CreateProjectInputDTO {
    private final String name;
    private final String description;
    private final String owner;

    public CreateProjectInputDTO(String name, String description, String owner) {
        this.name = name;
        this.description = description;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOwner() {
        return owner;
    }
}
