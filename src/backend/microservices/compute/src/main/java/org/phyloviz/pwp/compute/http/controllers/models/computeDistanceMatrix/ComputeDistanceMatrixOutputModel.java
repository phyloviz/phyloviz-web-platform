package org.phyloviz.pwp.compute.http.controllers.models.computeDistanceMatrix;

import lombok.Data;
import org.phyloviz.pwp.compute.service.dtos.computeDistanceMatrix.ComputeDistanceMatrixOutputDTO;

@Data
public class ComputeDistanceMatrixOutputModel {
    private String workflowId;

    public ComputeDistanceMatrixOutputModel(ComputeDistanceMatrixOutputDTO computeDistanceMatrixOutputDTO) {
        this.workflowId = computeDistanceMatrixOutputDTO.getWorkflowId();
    }
}
