package org.phyloviz.pwp.compute.http.controllers.models.getWorkflowStatus;

import lombok.Data;
import org.phyloviz.pwp.compute.service.dtos.getWorkflow.GetWorkflowStatusOutput;

import java.util.Map;

@Data
public class GetWorkflowStatusOutputModel {
    private String workflowId;
    private String type;
    private String status;
    private Map<String, Object> data;

    public GetWorkflowStatusOutputModel(GetWorkflowStatusOutput getWorkflowStatusOutput) {
        this.workflowId = getWorkflowStatusOutput.getWorkflowId();
        this.type = getWorkflowStatusOutput.getType();
        this.status = getWorkflowStatusOutput.getStatus();
        this.data = getWorkflowStatusOutput.getData();
    }
}
