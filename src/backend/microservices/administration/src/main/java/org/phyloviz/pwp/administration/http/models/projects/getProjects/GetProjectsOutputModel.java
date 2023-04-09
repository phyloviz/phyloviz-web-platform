package org.phyloviz.pwp.administration.http.models.projects.getProjects;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.projects.ProjectDTO;

import java.util.List;

@Data
public class GetProjectsOutputModel {
    private List<GetProjectsProjectModel> projects;

    public GetProjectsOutputModel(List<ProjectDTO> projectDTOs) {
        this.projects = projectDTOs.stream().map(GetProjectsProjectModel::new).toList();
    }
}
