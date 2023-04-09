package org.phyloviz.pwp.compute.service.flowviz.models.getWorkflow;

import lombok.Data;

import java.util.List;

@Data
public class AirflowData {

    private final List<WorkflowStatus> runs;
}