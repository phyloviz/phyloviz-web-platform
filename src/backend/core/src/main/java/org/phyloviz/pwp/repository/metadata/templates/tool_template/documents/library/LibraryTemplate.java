package org.phyloviz.pwp.repository.metadata.templates.tool_template.documents.library;

import lombok.Builder;
import lombok.Data;
import org.phyloviz.pwp.service.flowviz.models.tool.library.Library;

import java.util.List;

@Data
@Builder
public class LibraryTemplate {

    private final String name;
    private final int order;

    private final String invocation;
    private final boolean allowCommandRep;

    private final List<CommandTemplate> commands;

    public Library build() {
        return Library.builder()
                .order(order)
                .name(name)
                .invocation(invocation)
                .allowCommandRep(allowCommandRep)
                .commands(commands.stream().map(CommandTemplate::build).toList())
                .build();
    }
}
