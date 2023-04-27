package org.phyloviz.pwp.shared.repository.data.registry.distance_matrix;

import org.phyloviz.pwp.shared.repository.data.distance_matrix.DistanceMatrixDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.DistanceMatrixDataRepository;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.specific_data.DistanceMatrixDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.registry.DataRepositoryFactory;
import org.springframework.stereotype.Component;

@Component
public class DistanceMatrixDataRepositoryFactory extends
        DataRepositoryFactory<DistanceMatrixDataRepositoryId, DistanceMatrixDataRepository, DistanceMatrixDataRepositorySpecificData> {
    public DistanceMatrixDataRepositoryFactory(
            @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") DistanceMatrixDataRepositoryRegistry dataRepositoryRegistry
    ) {
        super(dataRepositoryRegistry);
    }
}
