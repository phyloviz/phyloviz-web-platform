package org.phyloviz.pwp.administration.http.models.datasets.deleteDataset;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteDatasetOutputModel {
    private String projectId;
    private String datasetId;
}
