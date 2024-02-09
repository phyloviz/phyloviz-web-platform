package org.phyloviz.pwp.service.flowviz.models.tool.access.library;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DockerAutoRemove {
    @SerializedName("never")
    NEVER,
    @SerializedName("success")
    SUCCESS,
    @SerializedName("force")
    FORCE
}
