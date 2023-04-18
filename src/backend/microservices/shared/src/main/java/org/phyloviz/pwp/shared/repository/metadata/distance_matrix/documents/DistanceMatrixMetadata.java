package org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.phyloviz.pwp.shared.adapters.distance_matrix.DistanceMatrixAdapterId;
import org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.specific_data.DistanceMatrixAdapterSpecificData;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.source.DistanceMatrixSource;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.source.DistanceMatrixSourceType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "#{@mongoMetadataCollectionNames.distanceMatrixMetadataCollection}")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistanceMatrixMetadata {
    @Id
    private String id;

    private String projectId;
    private String datasetId;
    private String distanceMatrixId;
    private String name;
    private DistanceMatrixSourceType sourceType;
    private DistanceMatrixSource source;
    private DistanceMatrixAdapterId adapterId;
    private DistanceMatrixAdapterSpecificData adapterSpecificData;

    public DistanceMatrixMetadata(String projectId, String datasetId, String distanceMatrixId, String name,
                                  DistanceMatrixSourceType sourceType, DistanceMatrixSource source,
                                  DistanceMatrixAdapterId adapterId,
                                  DistanceMatrixAdapterSpecificData adapterSpecificData) {
        this.projectId = projectId;
        this.datasetId = datasetId;
        this.distanceMatrixId = distanceMatrixId;
        this.name = name;
        this.sourceType = sourceType;
        this.source = source;
        this.adapterId = adapterId;
        this.adapterSpecificData = adapterSpecificData;
    }
}
