package org.phyloviz.pwp.uploader.http.controllers.models.createProject;

import lombok.Data;
import org.phyloviz.pwp.uploader.service.dtos.createProject.CreateProjectOutputDTO;

@Data
public class CreateProjectOutputModel {
    private String projectId;

    public CreateProjectOutputModel(CreateProjectOutputDTO createProjectOutputDTO) {
        this.projectId = createProjectOutputDTO.getProjectId();
    }
}
