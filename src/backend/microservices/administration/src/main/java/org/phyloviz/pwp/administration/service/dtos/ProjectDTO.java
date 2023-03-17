package org.phyloviz.pwp.administration.service.dtos;

import lombok.Data;
import org.phyloviz.pwp.administration.repository.project.Project;

@Data
public class ProjectDTO {
    private final String name;
    private final String description;
    private final String owner;
    private final FileDTO[] files;

    public ProjectDTO(Project project) {
        this.name = project.getName();
        this.description = project.getDescription();
        this.owner = project.getOwner();
        this.files = project.getFiles().stream().map(FileDTO::new).toArray(FileDTO[]::new);
    }
}
