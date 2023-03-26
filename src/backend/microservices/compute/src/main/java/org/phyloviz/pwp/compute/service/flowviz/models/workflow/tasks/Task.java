package org.phyloviz.pwp.compute.service.flowviz.models.workflow.tasks;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.phyloviz.pwp.compute.service.flowviz.models.workflow.tasks.Action;

@Data
@Builder
public class Task {

    private final String id;

    private final String tool;

    private final Action action;

    private final List<String> children;
}
