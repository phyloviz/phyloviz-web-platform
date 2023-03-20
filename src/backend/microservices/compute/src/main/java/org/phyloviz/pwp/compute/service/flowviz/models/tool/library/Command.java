package org.phyloviz.pwp.compute.service.flowviz.models.tool.library;

import java.util.List;
import lombok.Builder;
import lombok.Data;

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
