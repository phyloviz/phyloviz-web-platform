package org.phyloviz.pwp.uploader.repository.metadata.documents;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * Metadata of the uploaded file.
 */
@Data
public abstract class Metadata {
    @Id
    private String id;

    private final String resourceId;
    private final String projectId;
    private final String url;
    private final String adapterId;

    public Metadata(String resourceId, String projectId, String url, String adapter) {
        this.resourceId = resourceId;
        this.projectId = projectId;
        this.url = url;
        this.adapterId = adapter;
    }
}