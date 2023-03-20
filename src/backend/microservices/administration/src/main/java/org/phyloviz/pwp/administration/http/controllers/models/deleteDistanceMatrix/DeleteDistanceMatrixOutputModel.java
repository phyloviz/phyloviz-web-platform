package org.phyloviz.pwp.administration.http.controllers.models.deleteDistanceMatrix;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.deleteDistanceMatrix.DeleteDistanceMatrixOutputDTO;

@Data
public class DeleteDistanceMatrixOutputModel {
    private String projectId;
    private String distanceMatrixId;

    public DeleteDistanceMatrixOutputModel(DeleteDistanceMatrixOutputDTO deleteDistanceMatrixOutputDTO) {
        this.projectId = deleteDistanceMatrixOutputDTO.getProjectId();
        this.distanceMatrixId = deleteDistanceMatrixOutputDTO.getDistanceMatrixId();
    }
}
