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
    public Optional<DistanceMatrixMetadata> findAnyByProjectIdAndDatasetIdAndDistanceMatrixId(String projectId, String datasetId, String distanceMatrixId) {
        return distanceMatrixMetadataMongoRepository.findFirstByProjectIdAndDatasetIdAndDistanceMatrixId(
                projectId, datasetId, distanceMatrixId
        );
    }

    @Override
    public List<DistanceMatrixMetadata> findAllByDistanceMatrixId(String distanceMatrixId) {
        return distanceMatrixMetadataMongoRepository.findAllByDistanceMatrixId(distanceMatrixId);
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
    public List<DistanceMatrixMetadata> findAllByProjectIdAndDatasetIdAndDistanceMatrixId(String projectId, String datasetId, String distanceMatrixId) {
        return distanceMatrixMetadataMongoRepository.findAllByProjectIdAndDatasetIdAndDistanceMatrixId(projectId, datasetId, distanceMatrixId);
    }

    @Override
    public Optional<DistanceMatrixMetadata> findByProjectIdAndDatasetIdAndDistanceMatrixIdAndRepositoryId(
            String projectId, String datasetId, String distanceMatrixId, DistanceMatrixDataRepositoryId repositoryId
    ) {
        return distanceMatrixMetadataMongoRepository.findByProjectIdAndDatasetIdAndDistanceMatrixIdAndRepositoryId(
                projectId, datasetId, distanceMatrixId, repositoryId.name().toLowerCase()
        );
    }

    @Override
    public List<DistanceMatrixMetadata> findAllByProjectIdAndDatasetId(String projectId, String datasetId) {
        return distanceMatrixMetadataMongoRepository.findAllByProjectIdAndDatasetId(projectId, datasetId);
    }

    @Override
    public boolean existsByProjectIdAndDatasetIdAndDistanceMatrixId(String projectId, String datasetId, String distanceMatrixId) {
        return distanceMatrixMetadataMongoRepository.existsByProjectIdAndDatasetIdAndDistanceMatrixId(projectId, datasetId, distanceMatrixId);
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
