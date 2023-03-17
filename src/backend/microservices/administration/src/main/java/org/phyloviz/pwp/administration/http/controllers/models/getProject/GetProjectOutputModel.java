package org.phyloviz.pwp.administration.http.controllers.models.getProject;

import org.phyloviz.pwp.administration.http.controllers.models.ProjectModel;
import org.phyloviz.pwp.administration.service.dtos.ProjectDTO;

public class GetProjectOutputModel extends ProjectModel {
    public GetProjectOutputModel(ProjectDTO projectDTO) {
        super(projectDTO);
    }
}
