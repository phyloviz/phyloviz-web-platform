package org.phyloviz.pwp.administration.http.controllers.models.getProjects;

import java.util.List;
import lombok.Data;
import org.phyloviz.pwp.administration.http.controllers.models.ProjectModel;
import org.phyloviz.pwp.administration.service.dtos.ProjectDTO;

@Data
public class GetProjectsOutputModel {
    private final List<ProjectModel> projects;

    public GetProjectsOutputModel(List<ProjectDTO> projectDTOs) {
        this.projects = projectDTOs.stream().map(ProjectModel::new).toList();
    }
}
