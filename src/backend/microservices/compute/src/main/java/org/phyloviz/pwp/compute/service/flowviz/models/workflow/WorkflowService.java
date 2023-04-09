package org.phyloviz.pwp.compute.service.flowviz.models.workflow;

import org.phyloviz.pwp.compute.service.flowviz.FlowVizHttpService;
import org.phyloviz.pwp.compute.service.flowviz.exceptions.ConnectionRefusedException;
import org.phyloviz.pwp.compute.service.flowviz.exceptions.UnexpectedResponseException;
import org.phyloviz.pwp.compute.service.flowviz.models.getWorkflow.GetWorkflowResponse;

public class WorkflowService extends FlowVizHttpService {

    public WorkflowService(FlowVizHttpService httpService) {
        super(httpService);
    }

    public void postWorkflow(Workflow workflow) throws UnexpectedResponseException, ConnectionRefusedException {
        this.post("/workflow", workflow, Void.class);
    }

    public void getWorkflow(String id) throws UnexpectedResponseException, ConnectionRefusedException {
        this.get("/workflow/" + id, GetWorkflowResponse.class);
    }
}
