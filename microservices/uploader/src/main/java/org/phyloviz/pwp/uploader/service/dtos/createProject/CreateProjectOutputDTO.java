package org.phyloviz.pwp.uploader.service.dtos.createProject;

public class CreateProjectOutputDTO {
    private final String projectId;

    public CreateProjectOutputDTO(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }
}
