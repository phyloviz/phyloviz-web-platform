package org.phyloviz.pwp.administration.service.projects.datasets;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.service.dtos.datasets.DatasetDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.createDataset.CreateDatasetInputDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.createDataset.CreateDatasetOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.deleteDataset.DeleteDatasetInputDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.deleteDataset.DeleteDatasetOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.getDataset.GetDatasetInputDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.getDatasets.GetDatasetsInputDTO;
import org.phyloviz.pwp.administration.service.exceptions.EmptyDatasetNameException;
import org.phyloviz.pwp.administration.service.exceptions.EmptyTypingDataIdException;
import org.phyloviz.pwp.administration.service.exceptions.IsolateDataDoesNotExistException;
import org.phyloviz.pwp.administration.service.exceptions.TypingDataDoesNotExistException;
import org.phyloviz.pwp.administration.service.projects.datasets.distanceMatrices.DistanceMatricesService;
import org.phyloviz.pwp.administration.service.projects.datasets.treeViews.TreeViewsService;
import org.phyloviz.pwp.administration.service.projects.datasets.trees.TreesService;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.IsolateDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.IsolateDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.repository.metadata.typingData.TypingDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.typingData.documents.TypingDataMetadata;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.UnauthorizedException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Implementation of {@link DatasetsService} interface.
 */
@Service
@RequiredArgsConstructor
public class DatasetsServiceImpl implements DatasetsService {

    private final DatasetRepository datasetRepository;
    private final ProjectRepository projectRepository;
    private final TypingDataMetadataRepository typingDataMetadataRepository;
    private final IsolateDataMetadataRepository isolateDataMetadataRepository;

    private final DistanceMatricesService distanceMatricesService;
    private final TreesService treesService;
    private final TreeViewsService treeViewsService;

    @Override
    public CreateDatasetOutputDTO createDataset(CreateDatasetInputDTO createDatasetInputDTO) {
        String userId = createDatasetInputDTO.getUser().getId();
        String projectId = createDatasetInputDTO.getProjectId();
        String name = createDatasetInputDTO.getName();

        if (name == null || name.isBlank())
            throw new EmptyDatasetNameException();

        String typingDataId = createDatasetInputDTO.getTypingDataId();
        if (typingDataId == null || typingDataId.isBlank())
            throw new EmptyTypingDataIdException();

        String isolateDataId = createDatasetInputDTO.getIsolateDataId();
        if (isolateDataId == null || isolateDataId.isBlank())
            isolateDataId = null;

        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        if (!project.getOwnerId().equals(userId))
            throw new UnauthorizedException();

        TypingDataMetadata typingDataMetadata = typingDataMetadataRepository
                .findByTypingDataId(typingDataId)
                .orElseThrow(TypingDataDoesNotExistException::new);

        if (!typingDataMetadata.getProjectId().equals(projectId))
            throw new TypingDataDoesNotExistException();

        if (isolateDataId != null) {
            IsolateDataMetadata isolateDataMetadata = isolateDataMetadataRepository
                    .findByIsolateDataId(isolateDataId)
                    .orElseThrow(IsolateDataDoesNotExistException::new);

            if (!isolateDataMetadata.getProjectId().equals(projectId))
                throw new IsolateDataDoesNotExistException();
        }

        Dataset dataset = new Dataset(
                projectId,
                createDatasetInputDTO.getName(),
                createDatasetInputDTO.getDescription(),
                typingDataId,
                isolateDataId,
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );

        Dataset storedDataset = datasetRepository.save(dataset);
        String datasetId = storedDataset.getId();

        project.getDatasetIds().add(datasetId);
        projectRepository.save(project);

        return new CreateDatasetOutputDTO(projectId, datasetId);
    }

    @Override
    public DatasetDTO getDataset(GetDatasetInputDTO getDatasetInputDTO) {
        String userId = getDatasetInputDTO.getUser().getId();
        String projectId = getDatasetInputDTO.getProjectId();
        String datasetId = getDatasetInputDTO.getDatasetId();

        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        if (!project.getOwnerId().equals(userId))
            throw new UnauthorizedException();

        return getDataset(datasetId);
    }

    @Override
    public DeleteDatasetOutputDTO deleteDataset(DeleteDatasetInputDTO deleteDatasetInputDTO) {
        String userId = deleteDatasetInputDTO.getUser().getId();
        String projectId = deleteDatasetInputDTO.getProjectId();
        String datasetId = deleteDatasetInputDTO.getDatasetId();

        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        if (!project.getOwnerId().equals(userId))
            throw new UnauthorizedException();

        deleteDataset(datasetId);

        project.getDatasetIds().remove(datasetId);
        projectRepository.save(project);

        return new DeleteDatasetOutputDTO(projectId, datasetId);
    }

    @Override
    public List<DatasetDTO> getDatasets(GetDatasetsInputDTO getDatasetsInputDTO) {
        String userId = getDatasetsInputDTO.getUser().getId();
        String projectId = getDatasetsInputDTO.getProjectId();

        Project project = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        if (!project.getOwnerId().equals(userId))
            throw new UnauthorizedException();

        return project.getDatasetIds().stream().map(this::getDataset).toList();
    }

    @Override
    public DatasetDTO getDataset(String datasetId) {
        Dataset dataset = datasetRepository.findById(datasetId).orElseThrow(DatasetNotFoundException::new);

        return new DatasetDTO(
                dataset.getId(),
                dataset.getName(),
                dataset.getDescription(),
                dataset.getTypingDataId(),
                dataset.getIsolateDataId(),
                dataset.getDistanceMatrixIds().stream().map(distanceMatricesService::getDistanceMatrix).toList(),
                dataset.getTreeIds().stream().map(treesService::getTree).toList(),
                dataset.getTreeViewIds().stream().map(treeViewsService::getTreeView).toList()
        );
    }

    @Override
    public void deleteDataset(String datasetId) {
        Dataset dataset = datasetRepository.findById(datasetId).orElseThrow(DatasetNotFoundException::new);

        dataset.getTreeViewIds().forEach(treeViewsService::deleteTreeView);
        dataset.getTreeIds().forEach(treesService::deleteTree);
        dataset.getDistanceMatrixIds().forEach(distanceMatricesService::deleteDistanceMatrix);

        datasetRepository.delete(dataset);
    }
}
