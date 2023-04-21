package org.phyloviz.pwp.administration.http.service.project.dataset;

import org.phyloviz.pwp.shared.service.dtos.distance_matrix.DistanceMatrixInfo;

import java.util.List;

public interface DistanceMatrixService {

    List<DistanceMatrixInfo> getDistanceMatrixInfos(String datasetId);

    void deleteDistanceMatrix(String projectId, String datasetId, String distanceMatrixId, String userId);

    void deleteDistanceMatrix(String distanceMatrixId);
}
