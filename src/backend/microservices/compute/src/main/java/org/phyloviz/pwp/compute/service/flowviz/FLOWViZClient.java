package org.phyloviz.pwp.compute.service.flowviz;

import lombok.Getter;
import org.phyloviz.pwp.compute.service.flowviz.exceptions.AuthenticationException;
import org.phyloviz.pwp.compute.service.flowviz.exceptions.UnexpectedResponseException;
import org.phyloviz.pwp.compute.service.flowviz.identity.Credentials;
import org.phyloviz.pwp.compute.service.flowviz.identity.Token;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.ToolService;
import org.phyloviz.pwp.compute.service.flowviz.models.workflow.WorkflowService;

/**
 * Client for the FLOWViZ API.
 */
@Getter
public class FLOWViZClient extends FLOWViZHttpService {

    FLOWViZClient(String baseUrl, Credentials credentials) {
        super(baseUrl, credentials);
    }

    public static FLOWViZClientBuilder builder() {
        return new FLOWViZClientBuilder();
    }

    /**
     * Create a new tool service.
     *
     * @return the tool service
     */
    public ToolService toolService() {
        return new ToolService(this);
    }

    /**
     * Create a new workflow service.
     *
     * @return the workflow service
     */
    public WorkflowService workflowService() {
        return new WorkflowService(this);
    }
}
