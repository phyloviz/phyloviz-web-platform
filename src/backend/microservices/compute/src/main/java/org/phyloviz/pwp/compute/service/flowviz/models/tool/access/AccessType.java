package org.phyloviz.pwp.compute.service.flowviz.models.tool.access;

import com.google.gson.annotations.SerializedName;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AccessType {
    @SerializedName("library")
    LIBRARY,
    @SerializedName("api")
    API
}