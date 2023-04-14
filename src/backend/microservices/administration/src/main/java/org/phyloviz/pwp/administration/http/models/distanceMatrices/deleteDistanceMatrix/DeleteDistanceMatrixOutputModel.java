package org.phyloviz.pwp.administration.http.models.distanceMatrices.deleteDistanceMatrix;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.distanceMatrices.deleteDistanceMatrix.DeleteDistanceMatrixOutputDTO;

@Data
public class DeleteDistanceMatrixOutputModel {
    private String projectId;
    private String datasetId;
    private String distanceMatrixId;

    public DeleteDistanceMatrixOutputModel(DeleteDistanceMatrixOutputDTO deleteDistanceMatrixOutputDTO) {
        this.projectId = deleteDistanceMatrixOutputDTO.getProjectId();
        this.datasetId = deleteDistanceMatrixOutputDTO.getDatasetId();
        this.distanceMatrixId = deleteDistanceMatrixOutputDTO.getDistanceMatrixId();
    }
}
