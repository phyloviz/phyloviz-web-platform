package org.phyloviz.pwp.compute.repository.metadata.templates.tool_template.documents.access.library;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.text.StringSubstitutor;
import org.phyloviz.pwp.compute.repository.metadata.templates.tool_template.documents.ToolTemplateData;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.library.DockerVolume;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.library.VolumeType;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Data
@Builder
public class DockerVolumeTemplate {
    private final String source;

    private final String target;

    @Field("_type")
    private final VolumeType type;

    public DockerVolume buildDockerVolume(ToolTemplateData data) {

        Map<String, String> toolValues = data.toMap();

        StringSubstitutor sub = new StringSubstitutor(toolValues)
                .setEnableUndefinedVariableException(true);

        return DockerVolume.builder()
                .source(sub.replace(this.source))
                .target(sub.replace(this.target))
                .type(type)
                .build();
    }

    public DockerVolume buildDockerVolume() {
        return DockerVolume.builder()
                .source(source)
                .target(target)
                .type(type)
                .build();
    }

}