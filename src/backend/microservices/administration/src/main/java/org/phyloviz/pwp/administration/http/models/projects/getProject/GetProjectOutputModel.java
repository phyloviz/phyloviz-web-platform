package org.phyloviz.pwp.administration.http.models.projects.getProject;

import org.phyloviz.pwp.administration.http.models.projects.ProjectModel;
import org.phyloviz.pwp.shared.service.dtos.project.ProjectDTO;

public class GetProjectOutputModel extends ProjectModel {
    public GetProjectOutputModel(ProjectDTO projectDTO) {
        super(projectDTO);
    }
}
