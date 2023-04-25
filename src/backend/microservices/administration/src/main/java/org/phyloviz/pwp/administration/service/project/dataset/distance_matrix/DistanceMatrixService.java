package org.phyloviz.pwp.administration.service.project.dataset.distance_matrix;

import org.phyloviz.pwp.administration.service.dtos.distance_matrix.DistanceMatrixInfo;
import org.phyloviz.pwp.administration.service.dtos.distance_matrix.UpdateDistanceMatrixOutput;

import java.util.List;

public interface DistanceMatrixService {

    List<DistanceMatrixInfo> getDistanceMatrixInfos(String datasetId);

    void deleteDistanceMatrix(String projectId, String datasetId, String distanceMatrixId, String userId);

    void deleteAllByProjectIdAndDatasetId(String projectId, String datasetId);

    void deleteDistanceMatrix(String distanceMatrixId);

    UpdateDistanceMatrixOutput updateDistanceMatrix(String name, String projectId, String datasetId, String distanceMatrixId, String userId);
}
