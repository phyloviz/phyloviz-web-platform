package org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.phyloviz.pwp.shared.repository.metadata.Metadata;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collections;
import java.util.List;

@Document(collection = "distance-matrices")
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class DistanceMatrixMetadata extends Metadata {
    public List<String> inferenceTreeIds;

    public DistanceMatrixMetadata(String projectId, String resourceId, String url, String adapterId, Object adapterSpecificData) {
        super(projectId, resourceId, url, adapterId, adapterSpecificData);
        inferenceTreeIds = Collections.emptyList();
    }
}
