package org.phyloviz.pwp.administration.service.dtos.datasets.deleteDataset;

import lombok.Data;

@Data
public class DeleteDatasetOutputDTO {
    private final String projectId;
    private final String datasetId;
}
