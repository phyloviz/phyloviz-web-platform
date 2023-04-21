package org.phyloviz.pwp.shared.service.dtos.project;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.dataset.FullDatasetInfo;
import org.phyloviz.pwp.shared.service.dtos.files.FilesInfo;

import java.util.List;

@Data
public class FullProjectInfo {
    private final String projectId;
    private final String name;
    private final String description;
    private final String owner;
    private final List<FullDatasetInfo> datasets;
    private final FilesInfo files;
}
