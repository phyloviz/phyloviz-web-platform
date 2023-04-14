package org.phyloviz.pwp.compute.http.controllers.models.getWorkflowStatus;

import lombok.Data;
import org.phyloviz.pwp.compute.service.dtos.getWorkflow.GetWorkflowStatusOutputDTO;

import java.util.Map;

@Data
public class GetWorkflowStatusOutputModel {
    private String workflowId;
    private String type;
    private String status;
    private Map<String, Object> data;

    public GetWorkflowStatusOutputModel(GetWorkflowStatusOutputDTO getWorkflowStatusOutputDTO) {
        this.workflowId = getWorkflowStatusOutputDTO.getWorkflowId();
        this.type = getWorkflowStatusOutputDTO.getType();
        this.status = getWorkflowStatusOutputDTO.getStatus();
        this.data = getWorkflowStatusOutputDTO.getData();
    }
}
