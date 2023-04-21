package org.phyloviz.pwp.shared.service.project.dataset.distance_matrix;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.distance_matrix.DistanceMatrixAdapterId;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.DistanceMatrixMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;
import org.phyloviz.pwp.shared.service.exceptions.DistanceMatrixNotFoundException;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetMetadataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistanceMatrixMetadataServiceImpl implements DistanceMatrixMetadataService {

    private final DatasetMetadataService datasetMetadataService;

    private final DistanceMatrixMetadataRepository distanceMatrixMetadataRepository;

    @Override
    public DistanceMatrixMetadata getDistanceMatrixMetadata(String projectId, String datasetId, String distanceMatrixId, String userId) {
        Dataset dataset = datasetMetadataService.getDataset(projectId, datasetId, userId);

        if (!dataset.getDistanceMatrixIds().contains(distanceMatrixId)) {
            throw new DistanceMatrixNotFoundException();
        }

        return distanceMatrixMetadataRepository.findByDistanceMatrixId(distanceMatrixId).orElseThrow(DistanceMatrixNotFoundException::new);
    }

    @Override
    public DistanceMatrixMetadata getDistanceMatrixMetadata(String distanceMatrixId) {
        return distanceMatrixMetadataRepository.findByDistanceMatrixId(distanceMatrixId).orElseThrow(DistanceMatrixNotFoundException::new);
    }

    @Override
    public DistanceMatrixMetadata getDistanceMatrixMetadataOrNull(String distanceMatrixId) {
        return distanceMatrixMetadataRepository.findByDistanceMatrixId(distanceMatrixId).orElse(null);
    }

    @Override
    public DistanceMatrixMetadata getDistanceMatrixMetadataByAdapterIdOrNull(String distanceMatrixId, DistanceMatrixAdapterId adapterId) {
        return distanceMatrixMetadataRepository
                .findByDistanceMatrixIdAndAdapterId(distanceMatrixId, adapterId)
                .orElse(null);
    }

    @Override
    public List<DistanceMatrixMetadata> getAllDistanceMatrixMetadata(String distanceMatrixId) {
        return distanceMatrixMetadataRepository.findAllByDistanceMatrixId(distanceMatrixId);
    }

    @Override
    public List<DistanceMatrixMetadata> getAllDistanceMatrixMetadataByDatasetId(String datasetId) {
        return distanceMatrixMetadataRepository.findAllByDatasetId(datasetId);
    }

    @Override
    public void deleteDistanceMatrix(DistanceMatrixMetadata distanceMatrixMetadata) {
        distanceMatrixMetadataRepository.delete(distanceMatrixMetadata);
    }

    @Override
    public void assertExists(String projectId, String datasetId, String distanceMatrixId, String userId) {
        getDistanceMatrixMetadata(projectId, datasetId, distanceMatrixId, userId);
    }
}
