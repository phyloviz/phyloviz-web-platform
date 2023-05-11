package org.phyloviz.pwp.shared.repository.metadata.distance_matrix.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.DistanceMatrixDataRepositoryId;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.DistanceMatrixMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Primary
@RequiredArgsConstructor
public class DistanceMatrixMetadataRepositoryMongo implements DistanceMatrixMetadataRepository {

    private final DistanceMatrixMetadataMongoRepository distanceMatrixMetadataMongoRepository;

    @Override
    public Optional<DistanceMatrixMetadata> findByProjectIdAndDatasetIdAndDistanceMatrixId(String projectId, String datasetId, String distanceMatrixId) {
        return distanceMatrixMetadataMongoRepository.findByProjectIdAndDatasetIdAndDistanceMatrixId(
                projectId, datasetId, distanceMatrixId
        );
    }

    @Override
    public List<DistanceMatrixMetadata> findAllByDatasetId(String datasetId) {
        Set<String> seenDistanceMatrixIds = new HashSet<>();

        return distanceMatrixMetadataMongoRepository.findAllByDatasetId(datasetId).stream() // TODO change to mongodb query using aggregate (@Aggregation)
                .filter((distanceMatrixMetadata -> {
                    if (seenDistanceMatrixIds.contains(distanceMatrixMetadata.getDistanceMatrixId())) {
                        return false;
                    }
                    seenDistanceMatrixIds.add(distanceMatrixMetadata.getDistanceMatrixId());
                    return true;
                }))
                .toList();
    }

    @Override
    public List<DistanceMatrixMetadata> findAllByProjectIdAndDatasetId(String projectId, String datasetId) {
        return distanceMatrixMetadataMongoRepository.findAllByProjectIdAndDatasetId(projectId, datasetId);
    }

    @Override
    public void delete(DistanceMatrixMetadata distanceMatrixMetadata) {
        distanceMatrixMetadataMongoRepository.delete(distanceMatrixMetadata);
    }

    @Override
    public DistanceMatrixMetadata save(DistanceMatrixMetadata distanceMatrixMetadata) {
        return distanceMatrixMetadataMongoRepository.save(distanceMatrixMetadata);
    }
}
