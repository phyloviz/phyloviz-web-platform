package org.phyloviz.pwp.administration.service.project.dataset.distance_matrix;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.distance_matrix.DistanceMatrixAdapterFactory;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.DistanceMatrixMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree.TreeMetadataRepository;
import org.phyloviz.pwp.shared.service.dtos.distance_matrix.DistanceMatrixInfo;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.DeniedResourceDeletionException;
import org.phyloviz.pwp.shared.service.exceptions.DistanceMatrixNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistanceMatrixServiceImpl implements DistanceMatrixService {

    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;
    private final DistanceMatrixMetadataRepository distanceMatrixMetadataRepository;
    private final TreeMetadataRepository treeMetadataRepository;

    private final DistanceMatrixAdapterFactory distanceMatrixAdapterFactory;

    @Override
    public List<DistanceMatrixInfo> getDistanceMatrixInfos(String datasetId) {
        return distanceMatrixMetadataRepository.findAllByDatasetId(datasetId).stream()
                .map(DistanceMatrixInfo::new).toList();
    }

    @Override
    public void deleteDistanceMatrix(String projectId, String datasetId, String distanceMatrixId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();
        if (!datasetRepository.existsByProjectIdAndId(datasetId, projectId))
            throw new DatasetNotFoundException();

        if (!distanceMatrixMetadataRepository.existsByProjectIdAndDatasetIdAndDistanceMatrixId(projectId, datasetId, distanceMatrixId))
            throw new DistanceMatrixNotFoundException();

        if (treeMetadataRepository.existsByDatasetIdAndDistanceMatrixIdSource(datasetId, distanceMatrixId))
            throw new DeniedResourceDeletionException(
                    "Cannot delete distance matrix. It is a dependency of a tree. Delete the tree first."
            );

        deleteDistanceMatrix(distanceMatrixId);
    }

    @Override
    public void deleteAllByProjectIdAndDatasetId(String projectId, String datasetId) {
        distanceMatrixMetadataRepository.findAllByProjectIdAndDatasetId(projectId, datasetId)
                .forEach(distanceMatrixMetadata -> {
                    distanceMatrixAdapterFactory.getDistanceMatrixAdapter(distanceMatrixMetadata.getAdapterId())
                            .deleteDistanceMatrix(distanceMatrixMetadata.getAdapterSpecificData());

                    distanceMatrixMetadataRepository.delete(distanceMatrixMetadata);
                });
    }

    @Override
    public void deleteDistanceMatrix(String distanceMatrixId) {
        distanceMatrixMetadataRepository.findAllByDistanceMatrixId(distanceMatrixId)
                .forEach(distanceMatrixMetadata -> {
                    distanceMatrixAdapterFactory.getDistanceMatrixAdapter(distanceMatrixMetadata.getAdapterId())
                            .deleteDistanceMatrix(distanceMatrixMetadata.getAdapterSpecificData());

                    distanceMatrixMetadataRepository.delete(distanceMatrixMetadata);
                });
    }
}
