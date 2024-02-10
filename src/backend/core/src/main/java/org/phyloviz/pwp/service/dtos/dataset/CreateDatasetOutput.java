package org.phyloviz.pwp.service.dtos.dataset;

import lombok.Data;

@Data
public class CreateDatasetOutput {
    private final String projectId;
    private final String datasetId;
}
