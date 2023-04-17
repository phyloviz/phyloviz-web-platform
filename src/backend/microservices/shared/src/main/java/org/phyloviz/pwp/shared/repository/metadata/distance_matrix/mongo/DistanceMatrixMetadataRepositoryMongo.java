package org.phyloviz.pwp.shared.repository.metadata.distance_matrix.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.DistanceMatrixMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class DistanceMatrixMetadataRepositoryMongo implements DistanceMatrixMetadataRepository {

    private final DistanceMatrixMetadataMongoRepository distanceMatrixMetadataMongoRepository;

    @Override
    public void delete(DistanceMatrixMetadata distanceMatrixMetadata) {
        distanceMatrixMetadataMongoRepository.delete(distanceMatrixMetadata);
    }

    @Override
    public Optional<DistanceMatrixMetadata> findByDistanceMatrixId(String distanceMatrixId) {
        return distanceMatrixMetadataMongoRepository.findByDistanceMatrixId(distanceMatrixId);
    }

    @Override
    public List<DistanceMatrixMetadata> findAllByDistanceMatrixId(String distanceMatrixId) {
        return distanceMatrixMetadataMongoRepository.findAllByDistanceMatrixId(distanceMatrixId);
    }
}
