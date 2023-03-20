package org.phyloviz.pwp.compute.service.flowviz.models.tool.access.api;

import lombok.Data;
import lombok.NonNull;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.AccessDetails;

@Data
public class ApiAccess implements AccessDetails {
    
    private final String url;
    
    private final String apiKey;
}