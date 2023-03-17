package org.phyloviz.pwp.administration.http.controllers.models;

import java.util.Arrays;
import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.ProjectDTO;

@Data
public class ProjectModel {
    private String id;
    private String name;
    private String description;
    private String owner;
    private FileModel[] files;

    public ProjectModel(ProjectDTO projectDTO) {
        this.id = projectDTO.getId();
        this.name = projectDTO.getName();
        this.description = projectDTO.getDescription();
        this.owner = projectDTO.getOwner();
        this.files = Arrays.stream(projectDTO.getFiles()).map(FileModel::new).toArray(FileModel[]::new);
    }
}
