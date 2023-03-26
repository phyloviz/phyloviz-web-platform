package org.phyloviz.pwp.compute.service.flowviz.models.tool.access.library;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public enum DockerAutoRemove {
    @SerializedName("never")
    NEVER,
    @SerializedName("success")
    SUCCESS,
    @SerializedName("force")
    FORCE;
}
