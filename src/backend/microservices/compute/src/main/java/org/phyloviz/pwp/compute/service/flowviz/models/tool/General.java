package org.phyloviz.pwp.compute.service.flowviz.models.tool;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class General {
    
    private final String name;
    
    private final String description;
}
