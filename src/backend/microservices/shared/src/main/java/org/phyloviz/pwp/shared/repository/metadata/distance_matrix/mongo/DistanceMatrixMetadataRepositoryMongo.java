package org.phyloviz.pwp.shared.repository.metadata.distance_matrix.mongo;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.distance_matrix.DistanceMatrixAdapterId;
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
    public Optional<DistanceMatrixMetadata> findByDistanceMatrixId(String distanceMatrixId) {
        return distanceMatrixMetadataMongoRepository.findAllByDistanceMatrixId(distanceMatrixId).stream().findAny();
    }

    @Override
    public List<DistanceMatrixMetadata> findAllByDistanceMatrixId(String distanceMatrixId) {
        return distanceMatrixMetadataMongoRepository.findAllByDistanceMatrixId(distanceMatrixId);
    }

    @Override
    public Optional<DistanceMatrixMetadata> findByDistanceMatrixIdAndAdapterId(String distanceMatrixId, DistanceMatrixAdapterId adapterId) {
        return distanceMatrixMetadataMongoRepository.findByDistanceMatrixIdAndAdapterId(
                distanceMatrixId, adapterId.name().toLowerCase()
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
    public List<DistanceMatrixMetadata> findAllByProjectIdAndDatasetIdAndDistanceMatrixId(String projectId, String datasetId, String distanceMatrixId) {
        return distanceMatrixMetadataMongoRepository.findAllByProjectIdAndDatasetIdAndDistanceMatrixId(projectId, datasetId, distanceMatrixId);
    }

    @Override
    public Optional<DistanceMatrixMetadata> findByProjectIdAndDatasetIdAndDistanceMatrixIdAndAdapterId(
            String projectId, String datasetId, String distanceMatrixId, DistanceMatrixAdapterId adapterId
    ) {
        return distanceMatrixMetadataMongoRepository.findByProjectIdAndDatasetIdAndDistanceMatrixIdAndAdapterId(
                projectId, datasetId, distanceMatrixId, adapterId.name().toLowerCase()
        );
    }

    @Override
    public List<DistanceMatrixMetadata> findAllByProjectIdAndDatasetId(String projectId, String datasetId) {
        return distanceMatrixMetadataMongoRepository.findAllByProjectIdAndDatasetId(projectId, datasetId);
    }

    @Override
    public Boolean existsByProjectIdAndDatasetIdAndDistanceMatrixId(String projectId, String datasetId, String distanceMatrixId) {
        return distanceMatrixMetadataMongoRepository.existsByProjectIdAndDatasetIdAndDistanceMatrixId(projectId, datasetId, distanceMatrixId);
    }

    @Override
    public void delete(DistanceMatrixMetadata distanceMatrixMetadata) {
        distanceMatrixMetadataMongoRepository.delete(distanceMatrixMetadata);
    }
}
