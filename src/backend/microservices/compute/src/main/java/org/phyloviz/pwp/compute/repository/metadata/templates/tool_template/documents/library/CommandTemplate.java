package org.phyloviz.pwp.compute.repository.metadata.templates.tool_template.documents.library;

import lombok.Builder;
import lombok.Data;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.library.Command;

import java.util.List;

@Data
@Builder
public class CommandTemplate {

    private final String name;

    private final List<String> invocation;

    private final List<String> allowedValues;
    private final List<String> allowedCommandSets;
    private final String description;

    public Command build() {
        return Command.builder()
                .name(name)
                .invocation(this.invocation != null ? List.copyOf(this.invocation) : List.of())
                .allowedValues(this.allowedValues != null ? List.copyOf(this.allowedValues) : List.of())
                .allowedCommandSets(this.allowedCommandSets != null ? List.copyOf(this.allowedCommandSets) : List.of())
                .description(description)
                .build();
    }

}
