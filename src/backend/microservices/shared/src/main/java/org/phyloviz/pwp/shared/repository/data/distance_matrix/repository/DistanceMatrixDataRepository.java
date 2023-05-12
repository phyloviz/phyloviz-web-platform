package org.phyloviz.pwp.shared.repository.data.distance_matrix.repository;

import org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.specific_data.DistanceMatrixDataRepositorySpecificData;

public interface DistanceMatrixDataRepository {

    String getDistanceMatrix(DistanceMatrixDataRepositorySpecificData distanceMatrixDataRepositorySpecificData);

    void deleteDistanceMatrix(DistanceMatrixDataRepositorySpecificData distanceMatrixDataRepositorySpecificData);
}
