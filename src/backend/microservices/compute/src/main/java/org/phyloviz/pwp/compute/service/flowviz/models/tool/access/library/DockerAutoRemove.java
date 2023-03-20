package org.phyloviz.pwp.compute.service.flowviz.models.tool.access.library;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public enum DockerAutoRemove {
    NEVER("never"),
    SUCCESS("success"),
    FORCE("force");

    private final String value;
}
