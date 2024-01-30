package org.phyloviz.pwp.service.flowviz.models.tool.library;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public
class Command {

    private final String name;

    private final List<String> invocation;

    private final List<String> allowedValues;
    private final List<String> allowedCommandSets;
    private final String description;
}
