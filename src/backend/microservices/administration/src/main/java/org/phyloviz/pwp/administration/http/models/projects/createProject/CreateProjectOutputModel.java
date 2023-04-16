package org.phyloviz.pwp.administration.http.models.projects.createProject;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.project.CreateProjectOutput;

@Data
public class CreateProjectOutputModel {
    private String projectId;

    public CreateProjectOutputModel(CreateProjectOutput createProjectOutput) {
        this.projectId = createProjectOutput.getProjectId();
    }
}
