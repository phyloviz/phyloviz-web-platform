package org.phyloviz.pwp.shared.repository.metadata.typing_dataset.documents;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.phyloviz.pwp.shared.repository.metadata.Metadata;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Metadata for a saved typing dataset resource.
 */
@Document(collection = "typing-datasets")
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class TypingDatasetMetadata extends Metadata {
    public List<String> distanceMatrixIds;

    public TypingDatasetMetadata(String projectId, String resourceId, String url, String adapterId,
                                 Object adapterSpecificData, List<String> distanceMatrixIds) {
        super(projectId, resourceId, url, adapterId, adapterSpecificData);
        this.distanceMatrixIds = distanceMatrixIds;
    }
}
