package org.phyloviz.pwp.service.flowviz.models.tool;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class General {

    private final String name;

    private final String description;
}
