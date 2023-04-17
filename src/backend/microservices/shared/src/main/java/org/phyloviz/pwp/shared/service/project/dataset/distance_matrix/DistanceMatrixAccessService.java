package org.phyloviz.pwp.shared.service.project.dataset.distance_matrix;

import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;

public interface DistanceMatrixAccessService {

    DistanceMatrixMetadata getDistanceMatrixMetadata(String projectId, String datasetId, String distanceMatrixId, String userId);

    DistanceMatrixMetadata getDistanceMatrixMetadata(String distanceMatrixId);

    DistanceMatrixMetadata getDistanceMatrixMetadataOrNull(String distanceMatrixId);

    void deleteDistanceMatrix(String distanceMatrixId);

    void assertExists(String projectId, String datasetId, String distanceMatrixId, String userId);

    String getDistanceMatrix(String projectId, String datasetId, String distanceMatrixId, String userId);
}
