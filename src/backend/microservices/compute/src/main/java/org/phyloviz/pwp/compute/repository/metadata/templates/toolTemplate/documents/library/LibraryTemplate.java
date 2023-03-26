package org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.library;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.library.Library;

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
