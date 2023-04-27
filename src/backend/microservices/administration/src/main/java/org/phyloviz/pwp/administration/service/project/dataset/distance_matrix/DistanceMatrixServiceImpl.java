package org.phyloviz.pwp.administration.service.project.dataset.distance_matrix;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.service.dtos.distance_matrix.DistanceMatrixInfo;
import org.phyloviz.pwp.administration.service.dtos.distance_matrix.UpdateDistanceMatrixOutput;
import org.phyloviz.pwp.administration.service.exceptions.DeniedResourceDeletionException;
import org.phyloviz.pwp.shared.repository.data.registry.distance_matrix.DistanceMatrixDataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.DistanceMatrixMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree.TreeMetadataRepository;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.DistanceMatrixNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.InvalidArgumentException;
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

    private final DistanceMatrixDataRepositoryFactory distanceMatrixDataRepositoryFactory;

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
                    distanceMatrixDataRepositoryFactory.getRepository(distanceMatrixMetadata.getRepositoryId())
                            .deleteDistanceMatrix(distanceMatrixMetadata.getRepositorySpecificData());

                    distanceMatrixMetadataRepository.delete(distanceMatrixMetadata);
                });
    }

    @Override
    public void deleteDistanceMatrix(String distanceMatrixId) {
        distanceMatrixMetadataRepository.findAllByDistanceMatrixId(distanceMatrixId)
                .forEach(distanceMatrixMetadata -> {
                    distanceMatrixDataRepositoryFactory.getRepository(distanceMatrixMetadata.getRepositoryId())
                            .deleteDistanceMatrix(distanceMatrixMetadata.getRepositorySpecificData());

                    distanceMatrixMetadataRepository.delete(distanceMatrixMetadata);
                });
    }

    @Override
    public UpdateDistanceMatrixOutput updateDistanceMatrix(String name, String projectId, String datasetId, String distanceMatrixId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        if (!distanceMatrixMetadataRepository.existsByProjectIdAndDatasetIdAndDistanceMatrixId(projectId, datasetId, distanceMatrixId))
            throw new DistanceMatrixNotFoundException();

        String previousName = distanceMatrixMetadataRepository.findAnyByProjectIdAndDatasetIdAndDistanceMatrixId(projectId, datasetId, distanceMatrixId)
                .orElseThrow(DistanceMatrixNotFoundException::new)
                .getName();

        if (name == null)
            throw new InvalidArgumentException("You have to provide at least one field to update");

        if (name.equals(previousName))
            throw new InvalidArgumentException("The provided name is the same as the previous one");

        distanceMatrixMetadataRepository.findAllByProjectIdAndDatasetIdAndDistanceMatrixId(projectId, datasetId, distanceMatrixId)
                .forEach(distanceMatrixMetadata -> {
                    if (!name.isBlank())
                        distanceMatrixMetadata.setName(name);

                    distanceMatrixMetadataRepository.save(distanceMatrixMetadata);
                });

        return new UpdateDistanceMatrixOutput(previousName, name);
    }
}
