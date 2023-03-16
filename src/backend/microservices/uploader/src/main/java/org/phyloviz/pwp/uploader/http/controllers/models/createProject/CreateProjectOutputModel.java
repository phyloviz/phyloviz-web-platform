package org.phyloviz.pwp.uploader.http.controllers.models.createProject;

import org.phyloviz.pwp.uploader.service.dtos.createProject.CreateProjectOutputDTO;

public class CreateProjectOutputModel {
    private final String projectId;

    public CreateProjectOutputModel(String projectId) {
        this.projectId = projectId;
    }

    public CreateProjectOutputModel(CreateProjectOutputDTO createProjectOutputDTO) {
        this.projectId = createProjectOutputDTO.getProjectId();
    }
}
