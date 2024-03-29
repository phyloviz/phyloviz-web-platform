package org.phyloviz.pwp.service.flowviz.models.tool.access;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Access {
    public final AccessType type;
    public final AccessDetails details;
}