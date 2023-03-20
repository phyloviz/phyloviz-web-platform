package org.phyloviz.pwp.administration.http.controllers.models.createProject;


import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.createProject.CreateProjectOutputDTO;

@Data
public class CreateProjectOutputModel {
    private String projectId;

    public CreateProjectOutputModel(CreateProjectOutputDTO createProjectOutputDTO) {
        this.projectId = createProjectOutputDTO.getProjectId();
    }
}
