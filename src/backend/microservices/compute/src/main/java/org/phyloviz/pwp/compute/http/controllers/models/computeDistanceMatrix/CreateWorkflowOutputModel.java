package org.phyloviz.pwp.compute.http.controllers.models.computeDistanceMatrix;

import lombok.Data;
import org.phyloviz.pwp.compute.service.dtos.computeDistanceMatrix.CreateWorkflowOutputDTO;

@Data
public class CreateWorkflowOutputModel {
    private String workflowId;

    public CreateWorkflowOutputModel(CreateWorkflowOutputDTO createWorkflowOutputDTO) {
        this.workflowId = createWorkflowOutputDTO.getWorkflowId();
    }
}
