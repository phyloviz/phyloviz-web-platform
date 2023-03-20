package org.phyloviz.pwp.compute.service.flowviz.models.tool;

import org.phyloviz.pwp.compute.service.flowviz.exceptions.ConnectionRefusedException;
import org.phyloviz.pwp.compute.service.flowviz.FlowVizHttpService;
import org.phyloviz.pwp.compute.service.flowviz.exceptions.UnexpectedResponseException;

public class ToolService extends FlowVizHttpService {

    public ToolService(FlowVizHttpService httpService) {
        super(httpService);
    }

    public void postTool(Tool tool) throws UnexpectedResponseException, ConnectionRefusedException {
        this.post("/tool", tool, Void.class);
    }

    public void getTool(String id) throws UnexpectedResponseException, ConnectionRefusedException {
        this.get("/tool/" + id, Tool.class);
    }
}
