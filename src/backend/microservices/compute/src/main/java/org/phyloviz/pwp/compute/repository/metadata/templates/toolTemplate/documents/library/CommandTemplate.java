package org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.library;

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
        List<String> invocation = this.invocation != null ? this.invocation : List.of();
        List<String> allowedValues = this.allowedValues != null ? this.allowedValues : List.of();
        List<String> allowedCommandSets = this.allowedCommandSets != null ? this.allowedCommandSets : List.of();

        return Command.builder()
                .name(name)
                .invocation(List.copyOf(invocation))
                .allowedValues(List.copyOf(allowedValues))
                .allowedCommandSets(List.copyOf(allowedCommandSets))
                .description(description)
                .build();
    }

}
