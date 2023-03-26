package org.phyloviz.pwp.compute.service.flowviz.models.tool.access.api;

import lombok.Builder;
import lombok.Data;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.AccessDetails;

@Data
@Builder
public class ApiAccessDetails implements AccessDetails {
    
    private final String url;
    
    private final String apiKey;
}