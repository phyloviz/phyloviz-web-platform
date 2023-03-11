package uploader.repository.metadata.objects;

/**
 * Metadata for a saved profile dataset resource.
 */
public class ProfileMetadata extends Metadata {
    private final String originalFileName;

    public ProfileMetadata(String project, String path, String type, String originalFileName) {
        super(project, path, type);
        this.originalFileName = originalFileName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }
}
