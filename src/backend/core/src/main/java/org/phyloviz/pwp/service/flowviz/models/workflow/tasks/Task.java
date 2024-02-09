package org.phyloviz.pwp.service.flowviz.models.workflow.tasks;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Task {

    private final String id;

    private final String tool;

    private final Action action;

    private final List<String> children;
}
