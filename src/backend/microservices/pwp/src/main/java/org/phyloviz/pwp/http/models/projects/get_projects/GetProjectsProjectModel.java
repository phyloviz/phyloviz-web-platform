package org.phyloviz.pwp.http.models.projects.get_projects;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;

@Data
public class GetProjectsProjectModel {
    private String projectId;
    private String name;
    private String description;

    public GetProjectsProjectModel(Project project) {
        this.projectId = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
    }
}
