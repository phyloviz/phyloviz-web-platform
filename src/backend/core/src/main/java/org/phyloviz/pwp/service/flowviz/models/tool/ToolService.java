package org.phyloviz.pwp.service.flowviz.models.tool;

import org.phyloviz.pwp.service.flowviz.FLOWViZHttpService;
import org.phyloviz.pwp.service.flowviz.exceptions.ConnectionRefusedException;
import org.phyloviz.pwp.service.flowviz.exceptions.UnexpectedResponseException;

public class ToolService extends FLOWViZHttpService {

    public ToolService(FLOWViZHttpService httpService) {
        super(httpService);
    }

    public void postTool(Tool tool) throws UnexpectedResponseException, ConnectionRefusedException {
        this.post("/tool", tool, Void.class);
    }

    public void getTool(String id) throws UnexpectedResponseException, ConnectionRefusedException {
        this.get("/tool/" + id, Tool.class);
    }
}
