package org.phyloviz.pwp.administration.service.projects.datasets.distanceMatrices;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.repository.data.FileStorageRepository;
import org.phyloviz.pwp.administration.service.dtos.distance_matrices.DistanceMatrixDTO;
import org.phyloviz.pwp.administration.service.dtos.distance_matrices.deleteDistanceMatrix.DeleteDistanceMatrixInputDTO;
import org.phyloviz.pwp.administration.service.dtos.distance_matrices.deleteDistanceMatrix.DeleteDistanceMatrixOutputDTO;
import org.phyloviz.pwp.administration.service.exceptions.DeniedResourceDeletionException;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.DistanceMatrixMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.DistanceMatrixMetadata;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.repository.metadata.tree.TreeMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.source.TreeSourceAlgorithmDistanceMatrix;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.DistanceMatrixNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.UnauthorizedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistanceMatricesServiceImpl implements DistanceMatricesService {

    private final DistanceMatrixMetadataRepository distanceMatrixMetadataRepository;
    private final TreeMetadataRepository treeMetadataRepository;
    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;
    private final FileStorageRepository fileStorageRepository;

    @Override
    public DeleteDistanceMatrixOutputDTO deleteDistanceMatrix(DeleteDistanceMatrixInputDTO deleteDistanceMatrixInputDTO) {
        String projectId = deleteDistanceMatrixInputDTO.getProjectId();
        String datasetId = deleteDistanceMatrixInputDTO.getDatasetId();
        String distanceMatrixId = deleteDistanceMatrixInputDTO.getDistanceMatrixId();
        String userId = deleteDistanceMatrixInputDTO.getUser().getId();

        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        if (!project.getOwnerId().equals(userId))
            throw new UnauthorizedException();

        Dataset dataset = datasetRepository.findById(datasetId).orElseThrow(DatasetNotFoundException::new);

        dataset.getTreeIds().forEach(treeId -> {
            TreeMetadata treeMetadata = treeMetadataRepository.findByTreeId(treeId).orElse(null);

            if (treeMetadata != null && treeMetadata.getSourceType().equals("algorithmDistanceMatrix") &&
                    (((TreeSourceAlgorithmDistanceMatrix) treeMetadata.getSource())
                            .getDistanceMatrixId().equals(distanceMatrixId))) {
                throw new DeniedResourceDeletionException(
                        "Cannot delete distance matrix. " +
                                "It is a dependency of a tree (treeId = " + treeId + "). Delete the tree first."
                );
            }
        });

        distanceMatrixMetadataRepository
                .findByDistanceMatrixId(distanceMatrixId)
                .orElseThrow(DistanceMatrixNotFoundException::new);

        deleteDistanceMatrix(distanceMatrixId);

        dataset.getDistanceMatrixIds().remove(distanceMatrixId);
        datasetRepository.save(dataset);

        return new DeleteDistanceMatrixOutputDTO(projectId, datasetId, distanceMatrixId);
    }

    @Override
    public void deleteDistanceMatrix(String distanceMatrixId) {
        distanceMatrixMetadataRepository.findAllByDistanceMatrixId(distanceMatrixId)
                .forEach(distanceMatrixMetadata -> {
                    fileStorageRepository.delete(distanceMatrixMetadata.getUrl());

                    distanceMatrixMetadataRepository.delete(distanceMatrixMetadata);
                });
    }

    @Override
    public DistanceMatrixDTO getDistanceMatrix(String distanceMatrixId) {
        DistanceMatrixMetadata distanceMatrixMetadata =
                distanceMatrixMetadataRepository
                        .findByDistanceMatrixId(distanceMatrixId)
                        .orElseThrow(DistanceMatrixNotFoundException::new);

        return new DistanceMatrixDTO(distanceMatrixMetadata);
    }
}
