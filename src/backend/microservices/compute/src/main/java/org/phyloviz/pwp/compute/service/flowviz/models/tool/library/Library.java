package org.phyloviz.pwp.compute.service.flowviz.models.tool.library;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class Library {
    
    private final String name;
    private final int order;
    
    private final String invocation;
    private final boolean allowCommandRep;
    
    private final List<Command> commands;
}
