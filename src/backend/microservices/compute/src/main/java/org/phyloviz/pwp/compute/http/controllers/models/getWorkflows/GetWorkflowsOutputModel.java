package org.phyloviz.pwp.compute.http.controllers.models.getWorkflows;

import lombok.Data;
import org.phyloviz.pwp.compute.http.controllers.models.getWorkflowStatus.GetWorkflowStatusOutputModel;
import org.phyloviz.pwp.compute.service.dtos.getWorkflow.GetWorkflowStatusOutput;

import java.util.List;

@Data
public class GetWorkflowsOutputModel {
    private List<GetWorkflowStatusOutputModel> workflows;

    public GetWorkflowsOutputModel(List<GetWorkflowStatusOutput> getWorkflowStatusOutputDTO) {
        this.workflows = getWorkflowStatusOutputDTO.stream().map(GetWorkflowStatusOutputModel::new).toList();
    }
}
