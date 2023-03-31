package org.phyloviz.pwp.administration.service.dtos.projects;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.datasets.DatasetDTO;
import org.phyloviz.pwp.administration.service.dtos.files.FilesDTO;

import java.util.List;

@Data
public class ProjectDTO {
    private final String projectId;
    private final String name;
    private final String description;
    private final String owner;
    private final List<DatasetDTO> datasets;
    private final FilesDTO files;
}
