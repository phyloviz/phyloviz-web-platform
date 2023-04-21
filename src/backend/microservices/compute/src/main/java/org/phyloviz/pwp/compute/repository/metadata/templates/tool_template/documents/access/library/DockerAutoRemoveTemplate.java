package org.phyloviz.pwp.compute.repository.metadata.templates.tool_template.documents.access.library;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.library.DockerAutoRemove;

@Getter
@RequiredArgsConstructor
public enum DockerAutoRemoveTemplate {
    NEVER,
    SUCCESS,
    FORCE;

    public DockerAutoRemove build() {
        return DockerAutoRemove.valueOf(this.name());
    }
}
