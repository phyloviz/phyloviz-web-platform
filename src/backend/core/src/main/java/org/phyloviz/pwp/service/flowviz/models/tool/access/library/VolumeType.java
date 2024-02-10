package org.phyloviz.pwp.service.flowviz.models.tool.access.library;

import com.google.gson.annotations.SerializedName;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum VolumeType {
    @SerializedName("bind")
    BIND,
    @SerializedName("volume")
    VOLUME
}
