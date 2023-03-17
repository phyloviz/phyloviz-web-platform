package org.phyloviz.pwp.uploader.repository.metadata.documents;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Metadata for a saved profile dataset resource.
 */
@Document(collection = "profiles")
@Getter
@Setter
@ToString
public class ProfileMetadata extends Metadata {
    private final String originalFileName;

    public ProfileMetadata(String projectId, String resourceId, String location, String adapterId, String originalFileName) {
        super(projectId, resourceId, location, adapterId);
        this.originalFileName = originalFileName;
    }
}
