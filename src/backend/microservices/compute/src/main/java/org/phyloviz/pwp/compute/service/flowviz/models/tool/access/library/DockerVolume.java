package org.phyloviz.pwp.compute.service.flowviz.models.tool.access.library;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class DockerVolume {
    
    private final String source;
    
    private final String target;
    
    @SerializedName("_type")
    private final VolumeType type;
}
