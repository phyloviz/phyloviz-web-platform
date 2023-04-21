package org.phyloviz.pwp.shared.service.project.dataset.distance_matrix;

import org.phyloviz.pwp.shared.adapters.distance_matrix.DistanceMatrixAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;

import java.util.List;

public interface DistanceMatrixMetadataService {

    DistanceMatrixMetadata getDistanceMatrixMetadata(String projectId, String datasetId, String distanceMatrixId, String userId);

    DistanceMatrixMetadata getDistanceMatrixMetadata(String distanceMatrixId);

    DistanceMatrixMetadata getDistanceMatrixMetadataOrNull(String distanceMatrixId);

    DistanceMatrixMetadata getDistanceMatrixMetadataByAdapterIdOrNull(String distanceMatrixId, DistanceMatrixAdapterId adapterId);

    List<DistanceMatrixMetadata> getAllDistanceMatrixMetadata(String distanceMatrixId);

    /**
     * Find all distance matrix metadata from a dataset id. Only one distance matrix metadata per distance matrix resource.
     *
     * @param datasetId the id of the dataset
     * @return a list of distance matrix metadata
     */
    List<DistanceMatrixMetadata> getAllDistanceMatrixMetadataByDatasetId(String datasetId);

    void deleteDistanceMatrix(DistanceMatrixMetadata distanceMatrixMetadata);

    void assertExists(String projectId, String datasetId, String distanceMatrixId, String userId);
}
