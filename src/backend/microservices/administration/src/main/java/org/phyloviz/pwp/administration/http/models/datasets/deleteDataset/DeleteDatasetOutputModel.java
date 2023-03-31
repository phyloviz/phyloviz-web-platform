package org.phyloviz.pwp.administration.http.models.datasets.deleteDataset;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.datasets.deleteDataset.DeleteDatasetOutputDTO;

@Data
public class DeleteDatasetOutputModel {
    private String projectId;
    private String datasetId;

    public DeleteDatasetOutputModel(DeleteDatasetOutputDTO deleteDatasetOutputDTO) {
        this.projectId = deleteDatasetOutputDTO.getProjectId();
        this.datasetId = deleteDatasetOutputDTO.getDatasetId();
    }
}
