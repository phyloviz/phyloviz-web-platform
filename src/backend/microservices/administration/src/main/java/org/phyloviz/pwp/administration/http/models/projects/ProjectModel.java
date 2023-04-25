package org.phyloviz.pwp.administration.http.models.projects;

import lombok.Data;
import org.phyloviz.pwp.administration.http.models.datasets.DatasetModel;
import org.phyloviz.pwp.administration.http.models.files.FilesModel;
import org.phyloviz.pwp.administration.service.dtos.project.FullProjectInfo;

import java.util.List;

@Data
public class ProjectModel {
    private String projectId;
    private String name;
    private String description;
    private String owner;
    private List<DatasetModel> datasets;
    private FilesModel files;

    public ProjectModel(FullProjectInfo fullProjectInfo) {
        this.projectId = fullProjectInfo.getProjectId();
        this.name = fullProjectInfo.getName();
        this.description = fullProjectInfo.getDescription();
        this.owner = fullProjectInfo.getOwner();
        this.datasets = fullProjectInfo.getDatasets().stream().map(DatasetModel::new).toList();
        this.files = new FilesModel(fullProjectInfo.getFiles());
    }
}
