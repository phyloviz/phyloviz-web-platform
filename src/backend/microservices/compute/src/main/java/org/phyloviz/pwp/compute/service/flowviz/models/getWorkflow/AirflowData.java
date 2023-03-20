package org.phyloviz.pwp.compute.service.flowviz.models.getWorkflow;

import java.util.List;
import lombok.Data;

@Data
public class AirflowData {

    private final List<WorkflowStatus> runs;
}