package org.phyloviz.pwp.shared.service.project.dataset.distance_matrix;

import org.phyloviz.pwp.shared.service.dtos.distance_matrix.DistanceMatrixInfo;

public interface DistanceMatrixService {

    DistanceMatrixInfo getDistanceMatrixInfo(String distanceMatrixId);

    void deleteDistanceMatrix(String projectId, String datasetId, String distanceMatrixId, String userId);

    void deleteDistanceMatrix(String distanceMatrixId);

    String getDistanceMatrix(String projectId, String datasetId, String distanceMatrixId, String userId);
}
