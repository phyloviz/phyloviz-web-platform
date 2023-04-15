package org.phyloviz.pwp.shared.service.project.dataset;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.service.dtos.dataset.CreateDatasetOutput;
import org.phyloviz.pwp.shared.service.dtos.dataset.DatasetDTO;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.EmptyDatasetNameException;
import org.phyloviz.pwp.shared.service.exceptions.EmptyTypingDataIdException;
import org.phyloviz.pwp.shared.service.exceptions.InvalidIsolateDataIdException;
import org.phyloviz.pwp.shared.service.exceptions.IsolateDataDoesNotExistException;
import org.phyloviz.pwp.shared.service.exceptions.IsolateDataNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TypingDataDoesNotExistException;
import org.phyloviz.pwp.shared.service.exceptions.TypingDataNotFoundException;
import org.phyloviz.pwp.shared.service.project.ProjectService;
import org.phyloviz.pwp.shared.service.project.dataset.distanceMatrix.DistanceMatrixService;
import org.phyloviz.pwp.shared.service.project.dataset.tree.TreeService;
import org.phyloviz.pwp.shared.service.project.dataset.treeView.TreeViewService;
import org.phyloviz.pwp.shared.service.project.file.isolateData.IsolateDataService;
import org.phyloviz.pwp.shared.service.project.file.typingData.TypingDataService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatasetServiceImpl implements DatasetService {

    private final DatasetRepository datasetRepository;

    private final ProjectService projectService;
    private final DistanceMatrixService distanceMatrixService;
    private final TreeService treeService;
    private final TreeViewService treeViewService;
    private final TypingDataService typingDataService;
    private final IsolateDataService isolateDataService;

    @Override
    public CreateDatasetOutput createDataset(String name, String description, String typingDataId, String isolateDataId,
                                             String projectId, String userId) {
        validateCreateDatasetParameters(name, typingDataId, isolateDataId, projectId, userId);

        Project project = projectService.getProject(projectId, userId);

        Dataset dataset = new Dataset(
                projectId,
                name,
                description,
                typingDataId,
                isolateDataId,
                Collections.emptyList(),
                Collections.emptyList(),
                Collections.emptyList()
        );

        Dataset storedDataset = saveDataset(dataset);
        String datasetId = storedDataset.getId();

        project.getDatasetIds().add(datasetId);
        projectService.saveProject(project);

        return new CreateDatasetOutput(projectId, datasetId);
    }

    @Override
    public Dataset getDataset(String projectId, String datasetId, String userId) {
        Project project = projectService.getProject(projectId, userId);

        if (!project.getDatasetIds().contains(datasetId))
            throw new DatasetNotFoundException();

        return datasetRepository.findById(datasetId).orElseThrow(DatasetNotFoundException::new);
    }

    @Override
    public Dataset getDataset(String datasetId) {
        return datasetRepository.findById(datasetId).orElseThrow(DatasetNotFoundException::new);
    }

    @Override
    public Dataset getDatasetOrNull(String datasetId) {
        return datasetRepository.findById(datasetId).orElse(null);
    }

    @Override
    public DatasetDTO getDatasetDTO(String projectId, String datasetId, String userId) {
        return getDatasetDTO(getDataset(projectId, datasetId, userId));
    }

    @Override
    public DatasetDTO getDatasetDTO(String datasetId) {
        return getDatasetDTO(getDataset(datasetId));
    }

    @Override
    public List<Dataset> getDatasets(String projectId, String userId) {
        Project project = projectService.getProject(projectId, userId);

        return project.getDatasetIds().stream().map(this::getDataset).toList();
    }

    @Override
    public List<DatasetDTO> getDatasetDTOs(String projectId, String userId) {
        return getDatasets(projectId, userId).stream().map(this::getDatasetDTO).toList();
    }

    @Override
    public void assertExists(String projectId, String datasetId, String userId) {
        getDataset(projectId, datasetId, userId);
    }

    @Override
    public Dataset saveDataset(Dataset dataset) {
        return datasetRepository.save(dataset);
    }

    @Override
    public void deleteDataset(String projectId, String datasetId, String userId) {
        assertExists(projectId, datasetId, userId);

        Project project = projectService.getProject(projectId, userId);

        deleteDataset(datasetId);

        project.getDatasetIds().remove(datasetId);
        projectService.saveProject(project);
    }

    @Override
    public void deleteDataset(String datasetId) {
        Dataset dataset = getDataset(datasetId);

        dataset.getTreeViewIds().forEach(treeViewService::deleteTreeView);
        dataset.getTreeIds().forEach(treeService::deleteTree);
        dataset.getDistanceMatrixIds().forEach(distanceMatrixService::deleteDistanceMatrix);

        datasetRepository.delete(dataset);
    }

    /**
     * Gets a DatasetDTO from a Dataset, fetching the distance matrices, trees and tree views, since the Dataset only
     * stores the ids.
     */
    private DatasetDTO getDatasetDTO(Dataset dataset) {
        return new DatasetDTO(
                dataset.getId(),
                dataset.getName(),
                dataset.getDescription(),
                dataset.getTypingDataId(),
                dataset.getIsolateDataId(),
                dataset.getDistanceMatrixIds().stream().map(distanceMatrixService::getDistanceMatrixMetadataDTO).toList(),
                dataset.getTreeIds().stream().map(treeService::getTreeMetadataDTO).toList(),
                dataset.getTreeViewIds().stream().map(treeViewService::getTreeViewMetadataDTO).toList()
        );
    }

    private void validateCreateDatasetParameters(String name, String typingDataId, String isolateDataId, String projectId, String userId) {
        if (name == null || name.isBlank())
            throw new EmptyDatasetNameException();

        if (typingDataId == null || typingDataId.isBlank())
            throw new EmptyTypingDataIdException();

        if (isolateDataId != null && isolateDataId.isBlank())
            throw new InvalidIsolateDataIdException();

        projectService.assertExists(projectId, userId);

        try {
            typingDataService.assertExists(projectId, typingDataId, userId);
        } catch (TypingDataNotFoundException e) {
            throw new TypingDataDoesNotExistException();
        }

        if (isolateDataId != null) {
            try {
                isolateDataService.assertExists(projectId, isolateDataId, userId);
            } catch (IsolateDataNotFoundException e) {
                throw new IsolateDataDoesNotExistException();
            }
        }
    }
}
