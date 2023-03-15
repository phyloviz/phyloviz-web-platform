package org.isel.phylovizwebplatform.gateway.service.dtos;

public class CreateProjectOutputDTO {
    private final String projectId;

    public CreateProjectOutputDTO(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }
}
