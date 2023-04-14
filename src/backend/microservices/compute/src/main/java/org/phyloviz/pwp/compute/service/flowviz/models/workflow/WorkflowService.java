package org.phyloviz.pwp.compute.service.flowviz.models.workflow;

import org.phyloviz.pwp.compute.service.flowviz.FLOWViZHttpService;
import org.phyloviz.pwp.compute.service.flowviz.exceptions.ConnectionRefusedException;
import org.phyloviz.pwp.compute.service.flowviz.exceptions.UnexpectedResponseException;
import org.phyloviz.pwp.compute.service.flowviz.models.getWorkflow.GetWorkflowResponse;

public class WorkflowService extends FLOWViZHttpService {

    public WorkflowService(FLOWViZHttpService httpService) {
        super(httpService);
    }

    public void postWorkflow(Workflow workflow) throws UnexpectedResponseException, ConnectionRefusedException {
        this.post("/workflow", workflow, Void.class);
    }

    public GetWorkflowResponse getWorkflow(String id) throws UnexpectedResponseException, ConnectionRefusedException {
        return this.get("/workflow/" + id, GetWorkflowResponse.class);
    }
}
