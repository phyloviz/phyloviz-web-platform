package org.phyloviz.pwp.administration.http.models.projects.getProjects;

import java.util.List;
import lombok.Data;
import org.phyloviz.pwp.administration.http.models.projects.ProjectModel;
import org.phyloviz.pwp.administration.service.dtos.projects.ProjectDTO;

@Data
public class GetProjectsOutputModel {
    private List<GetProjectsProjectModel> projects;

    public GetProjectsOutputModel(List<ProjectDTO> projectDTOs) {
        this.projects = projectDTOs.stream().map(GetProjectsProjectModel::new).toList();
    }
}
