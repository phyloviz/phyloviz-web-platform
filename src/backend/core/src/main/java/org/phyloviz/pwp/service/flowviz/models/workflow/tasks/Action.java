package org.phyloviz.pwp.service.flowviz.models.workflow.tasks;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Action {
    private final String command;
}
