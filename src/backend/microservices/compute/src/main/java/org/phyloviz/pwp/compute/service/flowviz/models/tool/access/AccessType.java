package org.phyloviz.pwp.compute.service.flowviz.models.tool.access;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AccessType {
    LIBRARY("library"),
    API("api");

    private final String value;

    @Override
    public String toString() {
        return value;
    }
}