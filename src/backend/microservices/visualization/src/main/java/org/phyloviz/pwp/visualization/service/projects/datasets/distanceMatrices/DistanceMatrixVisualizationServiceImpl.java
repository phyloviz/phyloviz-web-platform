package org.phyloviz.pwp.visualization.service.projects.datasets.distanceMatrices;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.DistanceMatrixMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.distanceMatrix.documents.DistanceMatrixMetadata;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.DistanceMatrixNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.UnauthorizedException;
import org.phyloviz.pwp.shared.adapters.distanceMatrix.DistanceMatrixAdapter;
import org.phyloviz.pwp.shared.adapters.distanceMatrix.DistanceMatrixAdapterFactory;
import org.phyloviz.pwp.visualization.service.dtos.getDistanceMatrix.GetDistanceMatrixInputDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistanceMatrixVisualizationServiceImpl implements DistanceMatrixVisualizationService {

    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;
    private final DistanceMatrixMetadataRepository distanceMatrixMetadataRepository;

    private final DistanceMatrixAdapterFactory distanceMatrixAdapterFactory;

    private final List<String> getDistanceMatrixAdapterPriority = List.of("s3");

    @Override
    public String getDistanceMatrix(GetDistanceMatrixInputDTO getDistanceMatrixInputDTO) {
        String projectId = getDistanceMatrixInputDTO.getProjectId();
        String datasetId = getDistanceMatrixInputDTO.getDatasetId();
        String distanceMatrixId = getDistanceMatrixInputDTO.getDistanceMatrixId();

        validateParameters(projectId, datasetId, distanceMatrixId, getDistanceMatrixInputDTO.getUser().getId());

        DistanceMatrixMetadata distanceMatrix = getPrioritySortedDistanceMatrixMetadataList(distanceMatrixId).get(0);

        DistanceMatrixAdapter distanceMatrixAdapter = distanceMatrixAdapterFactory
                .getDistanceMatrixAdapter(distanceMatrix.getAdapterId());

        return distanceMatrixAdapter.getDistanceMatrix(distanceMatrix.getAdapterSpecificData());
    }

    private List<DistanceMatrixMetadata> getPrioritySortedDistanceMatrixMetadataList(String distanceMatrixId) {
        List<DistanceMatrixMetadata> distanceMatrixMetadataList = distanceMatrixMetadataRepository.findAllByDistanceMatrixId(distanceMatrixId);
        if (distanceMatrixMetadataList.isEmpty())
            throw new DistanceMatrixNotFoundException();

        distanceMatrixMetadataList.sort((o1, o2) -> {
            int i1 = getDistanceMatrixAdapterPriority.indexOf(o1.getAdapterId());
            if (i1 == -1)
                i1 = Integer.MAX_VALUE;

            int i2 = getDistanceMatrixAdapterPriority.indexOf(o2.getAdapterId());
            if (i2 == -1)
                i2 = Integer.MAX_VALUE;

            return Integer.compare(i1, i2);
        });

        return distanceMatrixMetadataList;
    }

    private void validateParameters(String projectId, String datasetId, String distanceMatrixId, String userId) {
        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        if (!project.getOwnerId().equals(userId))
            throw new UnauthorizedException();

        Dataset dataset = datasetRepository.findById(datasetId).orElseThrow(DatasetNotFoundException::new);

        if (!dataset.getProjectId().equals(projectId))
            throw new DatasetNotFoundException();

        dataset
                .getDistanceMatrixIds().stream()
                .filter(distanceMatrixId::equals)
                .findAny()
                .orElseThrow(DistanceMatrixNotFoundException::new);
    }
}
