package org.isel.phylovizwebplatform.gateway.http.models.createProject;

import org.isel.phylovizwebplatform.gateway.service.dtos.CreateProjectOutputDTO;

public class CreateProjectOutputModel {
    private final String projectId;

    public CreateProjectOutputModel(String projectId) {
        this.projectId = projectId;
    }

    public CreateProjectOutputModel(CreateProjectOutputDTO createProjectOutputDTO) {
        this.projectId = createProjectOutputDTO.getProjectId();
    }
}
