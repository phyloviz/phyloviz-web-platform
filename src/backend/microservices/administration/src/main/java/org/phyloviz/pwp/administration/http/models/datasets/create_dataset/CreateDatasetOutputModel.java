package org.phyloviz.pwp.administration.http.models.datasets.create_dataset;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.dataset.CreateDatasetOutput;

@Data
public class CreateDatasetOutputModel {
    private String projectId;
    private String datasetId;

    public CreateDatasetOutputModel(CreateDatasetOutput createDatasetOutput) {
        this.projectId = createDatasetOutput.getProjectId();
        this.datasetId = createDatasetOutput.getDatasetId();
    }
}
