package org.phyloviz.pwp.administration.http.models.projects;

import java.util.List;

import lombok.Data;
import org.phyloviz.pwp.administration.http.models.datasets.DatasetModel;
import org.phyloviz.pwp.administration.http.models.files.FilesModel;
import org.phyloviz.pwp.administration.service.dtos.projects.ProjectDTO;

@Data
public class ProjectModel {
    private String projectId;
    private String name;
    private String description;
    private String owner;
    private List<DatasetModel> datasets;
    private FilesModel files;

    public ProjectModel(ProjectDTO projectDTO) {
        this.projectId = projectDTO.getProjectId();
        this.name = projectDTO.getName();
        this.description = projectDTO.getDescription();
        this.owner = projectDTO.getOwner();
        this.datasets = projectDTO.getDatasets().stream().map(DatasetModel::new).toList();
        this.files = new FilesModel(projectDTO.getFiles());
    }
}
