package org.phyloviz.pwp.uploader.repository.metadata.documents;

/**
 * Metadata for a saved profile dataset resource.
 */
public class ProfileMetadata extends Metadata {
    private final String originalFileName;

    public ProfileMetadata(String projectId, String resourceId, String location, String adapterId, String originalFileName) {
        super(projectId, resourceId, location, adapterId);
        this.originalFileName = originalFileName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }
}
