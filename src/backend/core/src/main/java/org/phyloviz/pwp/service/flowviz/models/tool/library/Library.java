package org.phyloviz.pwp.service.flowviz.models.tool.library;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Library {

    private final String name;
    private final int order;

    private final String invocation;
    private final boolean allowCommandRep;

    private final List<Command> commands;
}
