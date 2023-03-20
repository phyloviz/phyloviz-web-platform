package org.phyloviz.pwp.compute.service.flowviz.models.workflow.tasks;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Action {

    private final String dockerImage;

    private final String dockerApiVersion;

    private final List<DockerVolume> dockerVolumes;

    private final String command;

    private final Boolean dockerAutoRemove;

    private final String dockerUrl;

    private final String dockerNetworkMode;
}
