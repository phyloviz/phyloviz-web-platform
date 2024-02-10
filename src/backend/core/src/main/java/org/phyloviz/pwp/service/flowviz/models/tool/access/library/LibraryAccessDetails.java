package org.phyloviz.pwp.service.flowviz.models.tool.access.library;

import lombok.Builder;
import lombok.Data;
import org.phyloviz.pwp.service.flowviz.models.tool.access.AccessDetails;

import java.util.List;

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

