package org.phyloviz.pwp.administration.service.projects.datasets;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.administration.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.administration.service.dtos.datasets.DatasetDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.createDataset.CreateDatasetInputDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.createDataset.CreateDatasetOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.deleteDataset.DeleteDatasetInputDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.deleteDataset.DeleteDatasetOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.getDataset.GetDatasetInputDTO;
import org.phyloviz.pwp.administration.service.dtos.datasets.getDatasets.GetDatasetsInputDTO;
import org.phyloviz.pwp.administration.service.projects.datasets.distance_matrices.DistanceMatricesService;
import org.phyloviz.pwp.administration.service.projects.datasets.tree_views.TreeViewsService;
import org.phyloviz.pwp.administration.service.projects.datasets.trees.TreesService;
import org.phyloviz.pwp.shared.service.exceptions.UnauthorizedException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DatasetsServiceImpl implements DatasetsService {

    private final DatasetRepository datasetRepository;
    private final ProjectRepository projectRepository;

    private final DistanceMatricesService distanceMatricesService;
    private final TreesService treesService;
    private final TreeViewsService treeViewsService;

    @Override
    public CreateDatasetOutputDTO createDataset(CreateDatasetInputDTO createDatasetInputDTO) {
        String userId = createDatasetInputDTO.getUser().getId();
        String projectId = createDatasetInputDTO.getProjectId();
        Project project = projectRepository.findById(projectId);

        if (!project.getOwnerId().equals(userId))
            throw new UnauthorizedException("User is not the owner of the project");

        Dataset dataset = new Dataset(
                createDatasetInputDTO.getProjectId(),
                createDatasetInputDTO.getName(),
                createDatasetInputDTO.getDescription(),
                createDatasetInputDTO.getTypingDataId(),
                createDatasetInputDTO.getIsolateDataId(),
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );

        Dataset storedDataset = datasetRepository.save(dataset);
        String datasetId = storedDataset.getId();

        project.getFileIds().getTypingDataIds().add(datasetId);
        projectRepository.save(project);

        return new CreateDatasetOutputDTO(projectId, datasetId);
    }

    @Override
    public DatasetDTO getDataset(GetDatasetInputDTO getDatasetInputDTO) {
        String userId = getDatasetInputDTO.getUser().getId();
        String projectId = getDatasetInputDTO.getProjectId();
        String datasetId = getDatasetInputDTO.getDatasetId();

        Project project = projectRepository.findById(projectId);

        if (!project.getOwnerId().equals(userId))
            throw new UnauthorizedException("User is not the owner of the project");

        return getDataset(datasetId);
    }

    public DeleteDatasetOutputDTO deleteDataset(DeleteDatasetInputDTO deleteDatasetInputDTO) {
        String userId = deleteDatasetInputDTO.getUser().getId();
        String datasetId = deleteDatasetInputDTO.getDatasetId();

        String projectId = datasetRepository.findById(datasetId).getProjectId();
        Project project = projectRepository.findById(projectId);

        if (!project.getOwnerId().equals(userId))
            throw new UnauthorizedException("User is not the owner of the project");

        deleteDataset(datasetId);

        project.getFileIds().getTypingDataIds().remove(datasetId); // TODO Remember to do this to all the other lists
        projectRepository.save(project);

        return new DeleteDatasetOutputDTO(projectId, datasetId);
    }

    @Override
    public List<DatasetDTO> getDatasets(GetDatasetsInputDTO getDatasetsInputDTO) {
        return null;
    }

    @Override
    public DatasetDTO getDataset(String datasetId) {
        return getDatasetDTO(datasetRepository.findById(datasetId));
    }

    public void deleteDataset(String datasetId) {
        Dataset dataset = datasetRepository.findById(datasetId);

        dataset.getDistanceMatrixIds().forEach(distanceMatricesService::deleteDistanceMatrix);
        dataset.getTreeIds().forEach(treesService::deleteTree);
        dataset.getTreeViewIds().forEach(treeViewsService::deleteTreeView);

        datasetRepository.delete(dataset);
    }

    private DatasetDTO getDatasetDTO(Dataset dataset) {
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
}
