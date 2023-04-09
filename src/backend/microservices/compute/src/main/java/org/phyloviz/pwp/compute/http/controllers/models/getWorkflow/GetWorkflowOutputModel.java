package org.phyloviz.pwp.compute.http.controllers.models.getWorkflow;

import lombok.Data;
import org.phyloviz.pwp.compute.service.dtos.getWorkflow.GetWorkflowOutputDTO;

import java.util.Map;

@Data
public class GetWorkflowOutputModel {
    private String workflowId;
    private String type;
    private String status;
    private Map<String, Object> data;

    public GetWorkflowOutputModel(GetWorkflowOutputDTO getWorkflowOutputDTO) {
        this.workflowId = getWorkflowOutputDTO.getWorkflowId();
        this.type = getWorkflowOutputDTO.getType();
        this.status = getWorkflowOutputDTO.getStatus();
        this.data = getWorkflowOutputDTO.getData();
    }
}
