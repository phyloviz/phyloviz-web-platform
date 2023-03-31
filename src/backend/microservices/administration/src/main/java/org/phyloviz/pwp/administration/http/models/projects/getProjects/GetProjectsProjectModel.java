package org.phyloviz.pwp.administration.http.models.projects.getProjects;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.projects.ProjectDTO;

@Data
public class GetProjectsProjectModel {
    private String projectId;
    private String name;
    private String description;

    public GetProjectsProjectModel(ProjectDTO projectDTO) {
        this.projectId = projectDTO.getProjectId();
        this.name = projectDTO.getName();
        this.description = projectDTO.getDescription();
    }
}
