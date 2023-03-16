package org.phyloviz.pwp.uploader.repository.metadata.objects;

import org.phyloviz.pwp.uploader.http.models.FileType;

/**
 * Metadata for a saved profile dataset resource.
 */
public class ProfileMetadata extends Metadata {
    private final String originalFileName;

    public ProfileMetadata(String projectId, String location, String originalFileName) {
        super(projectId, location, FileType.PROFILE);
        this.originalFileName = originalFileName;
    }

    public ProfileMetadata(String id, String projectId, String location, String originalFileName) {
        super(id, projectId, location, FileType.PROFILE);
        this.originalFileName = originalFileName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }
}
