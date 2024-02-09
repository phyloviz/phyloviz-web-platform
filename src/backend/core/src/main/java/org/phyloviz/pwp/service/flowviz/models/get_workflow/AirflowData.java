package org.phyloviz.pwp.service.flowviz.models.get_workflow;

import lombok.Data;

import java.util.List;

@Data
public class AirflowData {

    private final List<AirflowWorkflowStatus> runs;
}