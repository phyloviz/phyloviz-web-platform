package org.phyloviz.pwp.administration.http.models.projects.get_project;

import org.phyloviz.pwp.administration.http.models.projects.ProjectModel;
import org.phyloviz.pwp.shared.service.dtos.project.FullProjectInfo;

public class GetProjectOutputModel extends ProjectModel {
    public GetProjectOutputModel(FullProjectInfo fullProjectInfo) {
        super(fullProjectInfo);
    }
}
