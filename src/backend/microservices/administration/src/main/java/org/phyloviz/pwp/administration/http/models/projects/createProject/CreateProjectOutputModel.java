package org.phyloviz.pwp.administration.http.models.projects.createProject;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.projects.createProject.CreateProjectOutputDTO;

@Data
public class CreateProjectOutputModel {
    private String projectId;

    public CreateProjectOutputModel(CreateProjectOutputDTO createProjectOutputDTO) {
        this.projectId = createProjectOutputDTO.getProjectId();
    }
}
