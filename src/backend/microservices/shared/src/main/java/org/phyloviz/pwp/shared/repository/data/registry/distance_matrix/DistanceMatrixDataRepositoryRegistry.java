package org.phyloviz.pwp.shared.repository.data.registry.distance_matrix;

import org.phyloviz.pwp.shared.repository.data.distance_matrix.DistanceMatrixDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.DistanceMatrixDataRepository;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.specific_data.DistanceMatrixDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.registry.DataRepositoryRegistry;

public interface DistanceMatrixDataRepositoryRegistry extends
        DataRepositoryRegistry<DistanceMatrixDataRepositoryId, DistanceMatrixDataRepository, DistanceMatrixDataRepositorySpecificData> {
}
