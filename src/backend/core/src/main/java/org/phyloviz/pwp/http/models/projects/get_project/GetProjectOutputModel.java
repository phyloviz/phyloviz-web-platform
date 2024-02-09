package org.phyloviz.pwp.http.models.projects.get_project;

import org.phyloviz.pwp.http.models.projects.ProjectModel;
import org.phyloviz.pwp.service.dtos.project.FullProjectInfo;

public class GetProjectOutputModel extends ProjectModel {
    public GetProjectOutputModel(FullProjectInfo fullProjectInfo) {
        super(fullProjectInfo);
    }
}
