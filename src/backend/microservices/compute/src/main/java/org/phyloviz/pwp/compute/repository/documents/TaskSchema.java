package org.phyloviz.pwp.compute.repository.documents;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.phyloviz.pwp.compute.service.flowviz.models.workflow.tasks.Action;

@Data
@Builder
public class TaskSchema {

    private final String id;

    private final String tool;

    private final ActionSchema action;

    private final List<String> parents;

    private final List<String> children;
}
