package org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.DistanceMatrixDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.specific_data.DistanceMatrixDataRepositorySpecificData;
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
    private DistanceMatrixDataRepositoryId repositoryId;
    private DistanceMatrixDataRepositorySpecificData repositorySpecificData;

    public DistanceMatrixMetadata(String projectId, String datasetId, String distanceMatrixId, String name,
                                  DistanceMatrixSourceType sourceType, DistanceMatrixSource source,
                                  DistanceMatrixDataRepositoryId repositoryId,
                                  DistanceMatrixDataRepositorySpecificData repositorySpecificData) {
        this.projectId = projectId;
        this.datasetId = datasetId;
        this.distanceMatrixId = distanceMatrixId;
        this.name = name;
        this.sourceType = sourceType;
        this.source = source;
        this.repositoryId = repositoryId;
        this.repositorySpecificData = repositorySpecificData;
    }
}
