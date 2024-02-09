package org.phyloviz.pwp.service.flowviz.models.tool.access.library;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DockerVolume {

    private final String source;

    private final String target;

    @SerializedName("_type")
    private final VolumeType type;
}
