package org.phyloviz.pwp.uploader.repository.metadata.objects;

import org.springframework.data.annotation.Id;

/**
 * Metadata of the uploaded file.
 */
public abstract class Metadata {
    @Id
    private String id;
    private final String resourceId;
    private final String projectId;
    private final String location;
    private final String adapterId;

    public Metadata(String resourceId, String projectId, String location, String adapter) {
        this.resourceId = resourceId;
        this.projectId = projectId;
        this.location = location;
        this.adapterId = adapter;
    }

    public String getResourceId() {
        return resourceId;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getLocation() {
        return location;
    }

    public String getAdapterId() {
        return adapterId;
    }
}