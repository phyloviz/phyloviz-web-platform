package org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.access.library;

import com.google.gson.annotations.SerializedName;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.text.StringSubstitutor;
import org.phyloviz.pwp.compute.repository.metadata.templates.toolTemplate.documents.ToolTemplateData;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.library.DockerVolume;
import org.phyloviz.pwp.compute.service.flowviz.models.tool.access.library.VolumeType;
import org.springframework.data.mongodb.core.mapping.Field;

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

        String source = sub.replace(this.source);
        String target = sub.replace(this.target);

        return DockerVolume.builder()
                .source(source)
                .target(target)
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