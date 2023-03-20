package org.phyloviz.pwp.compute.service;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.compute.service.flowviz.FlowVizClient;
import org.phyloviz.pwp.compute.service.flowviz.models.workflow.Workflow;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link ComputeService} interface.
 */
@Service
@RequiredArgsConstructor
public class ComputeServiceImpl implements ComputeService {

    private final FlowVizClient flowVizClient;

    public ComputeServiceImpl() {
        this.flowVizClient = FlowVizClient
                .builder()
                .baseUrl("http://localhost:8080")
                .credentials("admin", "admin")
                .authenticate();

        this.flowVizClient.workflowService().postWorkflow(
                Workflow
                        .builder()
                .description("")
                        .build());
    }
}
