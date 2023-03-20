package org.phyloviz.pwp.compute.service.flowviz.models.workflow.tasks;

import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public
class DockerVolume {

    private final String source;

    private final String target;

    private final String _type;
}
