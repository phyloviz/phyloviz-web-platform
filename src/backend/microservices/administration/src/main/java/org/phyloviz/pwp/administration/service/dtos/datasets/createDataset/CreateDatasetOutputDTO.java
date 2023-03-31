package org.phyloviz.pwp.administration.service.dtos.datasets.createDataset;

import lombok.Data;

@Data
public class CreateDatasetOutputDTO {
    private final String projectId;
    private final String datasetId;
}
