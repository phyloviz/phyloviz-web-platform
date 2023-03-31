package org.phyloviz.pwp.administration.http.models.datasets.createDataset;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.datasets.createDataset.CreateDatasetOutputDTO;

@Data
public class CreateDatasetOutputModel {
    private String projectId;
    private String datasetId;

    public CreateDatasetOutputModel(CreateDatasetOutputDTO createDatasetOutputDTO) {
        this.projectId = createDatasetOutputDTO.getProjectId();
        this.datasetId = createDatasetOutputDTO.getDatasetId();
    }
}
