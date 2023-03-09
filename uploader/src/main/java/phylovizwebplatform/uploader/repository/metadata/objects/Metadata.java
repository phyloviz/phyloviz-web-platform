package phylovizwebplatform.uploader.repository.metadata.objects;

import org.springframework.data.annotation.Id;

/**
 * Represents the metadata for a saved resource.
 */
public abstract class Metadata {
    @Id
    private String id;

    private final String project;
    private final String location;
    private final String type;

    public Metadata(String project, String path, String type) {
        this.project = project;
        this.location = path;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getProject() {
        return project;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }
}