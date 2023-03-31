package org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.DistanceMatrixMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.DistanceMatrixMetadata;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public DistanceMatrixMetadata findByDistanceMatrixId(String distanceMatrixId) {
        return distanceMatrixMetadataMongoRepository.findByDistanceMatrixId(distanceMatrixId);
    }

    @Override
    public List<DistanceMatrixMetadata> findAllByResourceId(String distanceMatrixId) {
        return distanceMatrixMetadataMongoRepository.findAllByDistanceMatrixId(distanceMatrixId);
    }
}
