package org.phyloviz.pwp.shared.repository.metadata.distance_matrix.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.DistanceMatrixMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DistanceMatrixMetadataRepositoryMongo implements DistanceMatrixMetadataRepository {

    private final DistanceMatrixMetadataMongoRepository distanceMatrixMetadataMongoRepository;

    @Override
    public void deleteDistanceMatrix(DistanceMatrixMetadata distanceMatrixMetadata) {
        distanceMatrixMetadataMongoRepository.delete(distanceMatrixMetadata);
    }

    @Override
    public DistanceMatrixMetadata findByResourceId(String resourceId) {
        return distanceMatrixMetadataMongoRepository.findByResourceId(resourceId);
    }
}
