package org.phyloviz.pwp.http.models.datasets.delete_dataset;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteDatasetOutputModel {
    private String projectId;
    private String datasetId;
}
