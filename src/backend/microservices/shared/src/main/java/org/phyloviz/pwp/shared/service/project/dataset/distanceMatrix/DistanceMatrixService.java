package org.phyloviz.pwp.shared.service.project.dataset.distanceMatrix;

import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.DistanceMatrixMetadata;
import org.phyloviz.pwp.shared.service.dtos.distanceMatrix.DistanceMatrixMetadataDTO;

public interface DistanceMatrixService {

    DistanceMatrixMetadata getDistanceMatrixMetadata(String projectId, String datasetId, String distanceMatrixId, String userId);

    DistanceMatrixMetadata getDistanceMatrixMetadata(String distanceMatrixId);

    DistanceMatrixMetadata getDistanceMatrixMetadataOrNull(String distanceMatrixId);

    DistanceMatrixMetadataDTO getDistanceMatrixMetadataDTO(String distanceMatrixId);

    void assertExists(String projectId, String datasetId, String distanceMatrixId, String userId);

    void deleteDistanceMatrix(String projectId, String datasetId, String distanceMatrixId, String userId);

    void deleteDistanceMatrix(String distanceMatrixId);

    String getDistanceMatrix(String projectId, String datasetId, String distanceMatrixId, String userId);
}
