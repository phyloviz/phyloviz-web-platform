package org.phyloviz.pwp.http.models.projects.get_projects;

import lombok.Data;
import org.phyloviz.pwp.repository.metadata.project.documents.Project;

import java.util.List;

@Data
public class GetProjectsOutputModel {
    private List<GetProjectsProjectModel> projects;

    public GetProjectsOutputModel(List<Project> projects) {
        this.projects = projects.stream().map(GetProjectsProjectModel::new).toList();
    }
}
