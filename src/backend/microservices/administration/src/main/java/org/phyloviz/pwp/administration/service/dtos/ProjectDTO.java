package org.phyloviz.pwp.administration.service.dtos;

import lombok.Data;
import org.phyloviz.pwp.administration.repository.metadata.project.documents.Project;

@Data
public class ProjectDTO {
    private final String id;
    private final String name;
    private final String description;
    private final String owner;
    private final ResourceDTO[] resourceDTOs;

    public ProjectDTO(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        this.owner = project.getOwnerId();
        this.resourceDTOs = project.getResources().stream().map(ResourceDTO::new).toArray(ResourceDTO[]::new);
    }
}
