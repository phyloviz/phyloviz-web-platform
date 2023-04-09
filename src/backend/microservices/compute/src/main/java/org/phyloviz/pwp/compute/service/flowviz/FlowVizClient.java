package org.phyloviz.pwp.compute.service.flowviz;


import lombok.Getter;
import org.phyloviz.pwp.compute.service.flowviz.exceptions.AuthenticationException;
import org.phyloviz.pwp.compute.service.flowviz.exceptions.UnexpectedResponseException;
import org.phyloviz.pwp.compute.service.flowviz.identity.Credentials;
import org.phyloviz.pwp.compute.service.flowviz.identity.Token;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.ToolService;
import org.phyloviz.pwp.compute.service.flowviz.models.workflow.WorkflowService;

@Getter
public class FlowVizClient extends FlowVizHttpService {

    FlowVizClient(String baseUrl, Credentials credentials) {
        super(baseUrl);
        this.token = authenticate(credentials);
    }

    public static FlowVizClientBuilder builder() {
        return new FlowVizClientBuilder();
    }

    private Token authenticate(Credentials credentials) {
        try {
            return this.post("/login", credentials, Token.class);
        } catch (UnexpectedResponseException e) {
            throw new AuthenticationException("Failed to authenticate");
        }
    }

    public ToolService toolService() {
        return new ToolService(this);
    }

    public WorkflowService workflowService() {
        return new WorkflowService(this);
    }

}
