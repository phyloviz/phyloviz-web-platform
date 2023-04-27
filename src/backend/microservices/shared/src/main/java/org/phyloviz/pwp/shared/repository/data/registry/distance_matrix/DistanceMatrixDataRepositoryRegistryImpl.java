package org.phyloviz.pwp.shared.repository.data.registry.distance_matrix;

import org.phyloviz.pwp.shared.repository.data.distance_matrix.DistanceMatrixDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.DistanceMatrixDataRepository;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.specific_data.DistanceMatrixDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.registry.AbstractDataRepositoryRegistry;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public class DistanceMatrixDataRepositoryRegistryImpl
        extends AbstractDataRepositoryRegistry<DistanceMatrixDataRepositoryId, DistanceMatrixDataRepository, DistanceMatrixDataRepositorySpecificData>
        implements DistanceMatrixDataRepositoryRegistry {
    public DistanceMatrixDataRepositoryRegistryImpl(
            ApplicationContext context,
            Map<DistanceMatrixDataRepositoryId, Class<? extends DistanceMatrixDataRepository>> repositoryClasses,
            Map<DistanceMatrixDataRepositoryId, Class<? extends DistanceMatrixDataRepositorySpecificData>> repositorySpecificDataClasses
    ) {
        super(context, DistanceMatrixDataRepositoryId.class, repositoryClasses, repositorySpecificDataClasses);
    }
}