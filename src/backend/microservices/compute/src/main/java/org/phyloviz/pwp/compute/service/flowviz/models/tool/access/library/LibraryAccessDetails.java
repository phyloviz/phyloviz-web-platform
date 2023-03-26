package org.phyloviz.pwp.compute.service.flowviz.models.tool.access.library;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.AccessDetails;

@Data
@Builder
public class LibraryAccessDetails implements AccessDetails {
    
    private final String address;
    
    private final String dockerUrl;
    
    private final String dockerImage;
    
    private final String dockerContainer;
    
    private final DockerAutoRemove dockerAutoRemove;
    
    private final String dockerNetworkMode;
    
    private final String dockerApiVersion;
    
    private final List<DockerVolume> dockerVolumes;
}

