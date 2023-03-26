package org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents;

import lombok.Builder;
import lombok.Data;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.General;

@Data
@Builder
public class GeneralTemplate {

    private final String name;

    private final String description;

    public General build(ToolTemplateData data) {
        return General.builder()
                .name(name + "-" + data.getWorkflowId())
                .description(description)
                .build();
    }
}
