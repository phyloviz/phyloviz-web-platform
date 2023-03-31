package org.phyloviz.pwp.administration.service.projects.datasets.distance_matrices;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.repository.data.FileStorageRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.administration.service.dtos.distance_matrices.DistanceMatrixDTO;
import org.phyloviz.pwp.administration.service.dtos.distance_matrices.deleteDistanceMatrix.DeleteDistanceMatrixInputDTO;
import org.phyloviz.pwp.administration.service.dtos.distance_matrices.deleteDistanceMatrix.DeleteDistanceMatrixOutputDTO;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.DistanceMatrixMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.DistanceMatrixMetadata;
import org.phyloviz.pwp.shared.service.exceptions.UnauthorizedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistanceMatricesServiceImpl implements DistanceMatricesService {

    private final DistanceMatrixMetadataRepository distanceMatrixMetadataRepository;
    private final ProjectRepository projectRepository;
    private final FileStorageRepository fileStorageRepository;

    @Override
    public DeleteDistanceMatrixOutputDTO deleteDistanceMatrix(DeleteDistanceMatrixInputDTO deleteDistanceMatrixInputDTO) {
        String projectId = deleteDistanceMatrixInputDTO.getProjectId();
        String datasetId = deleteDistanceMatrixInputDTO.getDatasetId();
        String distanceMatrixId = deleteDistanceMatrixInputDTO.getDistanceMatrixId();
        String userId = deleteDistanceMatrixInputDTO.getUser().getId();

        Project project = projectRepository.findById(projectId);

        if (!project.getOwnerId().equals(userId))
            throw new UnauthorizedException("User is not the owner of the project");

        deleteDistanceMatrix(distanceMatrixId);

        return new DeleteDistanceMatrixOutputDTO(projectId, datasetId, distanceMatrixId);
    }

    @Override
    public void deleteDistanceMatrix(String distanceMatrixId) {
        distanceMatrixMetadataRepository.findAllByResourceId(distanceMatrixId)
                .forEach(distanceMatrixMetadata -> {
                    fileStorageRepository.delete(distanceMatrixMetadata.getUrl());

                    distanceMatrixMetadataRepository.delete(distanceMatrixMetadata);
                });
    }

    @Override
    public DistanceMatrixDTO getDistanceMatrix(String distanceMatrixId) {
        DistanceMatrixMetadata distanceMatrixMetadata = distanceMatrixMetadataRepository.findByDistanceMatrixId(distanceMatrixId);

        return new DistanceMatrixDTO(distanceMatrixMetadata);
    }
}
