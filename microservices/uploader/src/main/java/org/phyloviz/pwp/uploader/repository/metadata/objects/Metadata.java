package org.phyloviz.pwp.uploader.repository.metadata.objects;

import org.phyloviz.pwp.uploader.http.models.FileType;
import org.springframework.data.annotation.Id;

/**
 * Metadata of the uploaded file.
 */
public abstract class Metadata {
    @Id
    private String id;

    private final String projectId;
    private final String location;
    private final FileType type;

    public Metadata(String projectId, String location, FileType type) {
        this.projectId = projectId;
        this.location = location;
        this.type = type;
    }

    public Metadata(String id, String projectId, String location, FileType type) {
        this.id = id;
        this.projectId = projectId;
        this.location = location;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getLocation() {
        return location;
    }

    public FileType getType() {
        return type;
    }
}