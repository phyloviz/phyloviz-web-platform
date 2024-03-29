package org.phyloviz.pwp.http.models.get_workflows;

import lombok.Data;
import org.phyloviz.pwp.http.models.get_workflow_status.GetWorkflowStatusOutputModel;
import org.phyloviz.pwp.service.dtos.get_workflow.GetWorkflowStatusOutput;

import java.util.List;

@Data
public class GetWorkflowsOutputModel {
    private List<GetWorkflowStatusOutputModel> workflows;

    public GetWorkflowsOutputModel(List<GetWorkflowStatusOutput> getWorkflowStatusOutput) {
        this.workflows = getWorkflowStatusOutput.stream().map(GetWorkflowStatusOutputModel::new).toList();
    }
}
