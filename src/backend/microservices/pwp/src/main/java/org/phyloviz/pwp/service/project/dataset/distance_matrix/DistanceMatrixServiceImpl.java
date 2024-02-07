package org.phyloviz.pwp.service.project.dataset.distance_matrix;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.service.dtos.distance_matrix.DistanceMatrixInfo;
import org.phyloviz.pwp.service.dtos.distance_matrix.UpdateDistanceMatrixOutput;
import org.phyloviz.pwp.service.exceptions.DeniedResourceDeletionException;
import org.phyloviz.pwp.repository.data.registry.distance_matrix.DistanceMatrixDataRepositoryFactory;
import org.phyloviz.pwp.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.repository.metadata.distance_matrix.DistanceMatrixMetadataRepository;
import org.phyloviz.pwp.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;
import org.phyloviz.pwp.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.repository.metadata.tree.TreeMetadataRepository;
import org.phyloviz.pwp.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.service.exceptions.DistanceMatrixNotFoundException;
import org.phyloviz.pwp.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.service.exceptions.ProjectNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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
        if (!datasetRepository.existsByProjectIdAndId(projectId, datasetId))
            throw new DatasetNotFoundException();

        DistanceMatrixMetadata distanceMatrixMetadata = distanceMatrixMetadataRepository
                .findByProjectIdAndDatasetIdAndDistanceMatrixId(projectId, datasetId, distanceMatrixId)
                .orElseThrow(DistanceMatrixNotFoundException::new);

        if (treeMetadataRepository.existsByDatasetIdAndDistanceMatrixIdSource(datasetId, distanceMatrixId))
            throw new DeniedResourceDeletionException(
                    "Cannot delete distance matrix. It is a dependency of a tree. Delete the tree first."
            );

        deleteDistanceMatrix(distanceMatrixMetadata);
    }

    @Override
    public void deleteAllByProjectIdAndDatasetId(String projectId, String datasetId) {
        distanceMatrixMetadataRepository.findAllByProjectIdAndDatasetId(projectId, datasetId)
                .forEach(this::deleteDistanceMatrix);
    }

    @Override
    public UpdateDistanceMatrixOutput updateDistanceMatrix(String name, String projectId, String datasetId, String distanceMatrixId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();
        if (!datasetRepository.existsByProjectIdAndId(projectId, datasetId))
            throw new DatasetNotFoundException();

        DistanceMatrixMetadata distanceMatrixMetadata = distanceMatrixMetadataRepository
                .findByProjectIdAndDatasetIdAndDistanceMatrixId(projectId, datasetId, distanceMatrixId)
                .orElseThrow(DistanceMatrixNotFoundException::new);

        String previousName = distanceMatrixMetadata.getName();

        if (name == null)
            throw new InvalidArgumentException("You have to provide at least one field to update");

        if (name.isBlank())
            throw new InvalidArgumentException("Name can't be blank");

        if (!name.equals(previousName)) {
            distanceMatrixMetadata.setName(name);
            distanceMatrixMetadataRepository.save(distanceMatrixMetadata);
        }

        return new UpdateDistanceMatrixOutput(previousName, name);
    }

    private void deleteDistanceMatrix(DistanceMatrixMetadata distanceMatrixMetadata) {
        distanceMatrixMetadata.getRepositorySpecificData().forEach((repositoryId, repositorySpecificData) ->
                distanceMatrixDataRepositoryFactory.getRepository(repositoryId)
                        .deleteDistanceMatrix(repositorySpecificData)
        );

        distanceMatrixMetadataRepository.delete(distanceMatrixMetadata);
    }
}
