package org.phyloviz.pwp.administration.http.service.project.dataset;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.project.documents.Project;
import org.phyloviz.pwp.shared.service.dtos.dataset.CreateDatasetOutput;
import org.phyloviz.pwp.shared.service.dtos.dataset.FullDatasetInfo;
import org.phyloviz.pwp.shared.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.shared.service.exceptions.IsolateDataDoesNotExistException;
import org.phyloviz.pwp.shared.service.exceptions.IsolateDataNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TypingDataDoesNotExistException;
import org.phyloviz.pwp.shared.service.exceptions.TypingDataNotFoundException;
import org.phyloviz.pwp.shared.service.project.ProjectMetadataService;
import org.phyloviz.pwp.shared.service.project.dataset.DatasetMetadataService;
import org.phyloviz.pwp.shared.service.project.file.isolate_data.IsolateDataMetadataService;
import org.phyloviz.pwp.shared.service.project.file.typing_data.TypingDataMetadataService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatasetServiceImpl implements DatasetService {

    private final ProjectMetadataService projectMetadataService;
    private final DatasetMetadataService datasetMetadataService;
    private final TypingDataMetadataService typingDataMetadataService;
    private final IsolateDataMetadataService isolateDataMetadataService;

    private final DistanceMatrixService distanceMatrixService;
    private final TreeService treeService;
    private final TreeViewService treeViewService;

    @Override
    public CreateDatasetOutput createDataset(String name, String description, String typingDataId, String isolateDataId,
                                             String projectId, String userId) {
        validateCreateDatasetParameters(name, typingDataId, isolateDataId, projectId, userId);

        Project project = projectMetadataService.getProject(projectId, userId);

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

        Dataset storedDataset = datasetMetadataService.saveDataset(dataset);
        String datasetId = storedDataset.getId();

        project.getDatasetIds().add(datasetId);
        projectMetadataService.saveProject(project);

        return new CreateDatasetOutput(projectId, datasetId);
    }

    @Override
    public FullDatasetInfo getFullDatasetInfo(String projectId, String datasetId, String userId) {
        return getFullDatasetInfo(datasetMetadataService.getDataset(projectId, datasetId, userId));
    }

    @Override
    public List<FullDatasetInfo> getFullDatasetInfos(String projectId, String userId) {
        projectMetadataService.assertExists(projectId, userId);

        return getFullDatasetInfos(projectId);
    }

    @Override
    public List<FullDatasetInfo> getFullDatasetInfos(String projectId) {
        return datasetMetadataService.getAllDatasetsByProjectId(projectId)
                .stream().map(this::getFullDatasetInfo).toList();
    }

    @Override
    public void deleteDataset(String projectId, String datasetId, String userId) {
        datasetMetadataService.assertExists(projectId, datasetId, userId);

        Project project = projectMetadataService.getProject(projectId, userId);

        deleteDataset(datasetId);

        project.getDatasetIds().remove(datasetId);
        projectMetadataService.saveProject(project);
    }

    @Override
    public void deleteDataset(String datasetId) {
        Dataset dataset = datasetMetadataService.getDataset(datasetId);

        dataset.getTreeViewIds().forEach(treeViewService::deleteTreeView);
        dataset.getTreeIds().forEach(treeService::deleteTree);
        dataset.getDistanceMatrixIds().forEach(distanceMatrixService::deleteDistanceMatrix);

        /*
        treeViewService.deleteAllByDatasetId(datasetId); TODO potentially implement this, removing ids from the dataset, do the same for project
        treeService.deleteAllByDatasetId(datasetId);
        distanceMatrixService.deleteAllByDatasetId(datasetId);
         */

        datasetMetadataService.deleteDataset(datasetId);
    }

    /**
     * Gets the Full Dataset Info from a Dataset, fetching the distance matrices, trees and tree views of the dataset,
     * since the Dataset only stores the ids.
     */
    private FullDatasetInfo getFullDatasetInfo(Dataset dataset) {
        return new FullDatasetInfo(
                dataset.getId(),
                dataset.getName(),
                dataset.getDescription(),
                dataset.getTypingDataId(),
                dataset.getIsolateDataId(),
                distanceMatrixService.getDistanceMatrixInfos(dataset.getId()),
                treeService.getTreeInfos(dataset.getId()),
                treeViewService.getTreeViewInfos(dataset.getId())
        );
    }

    private void validateCreateDatasetParameters(String name, String typingDataId, String isolateDataId, String projectId, String userId) {
        if (name == null || name.isBlank())
            throw new InvalidArgumentException("Dataset name cannot be empty");

        if (typingDataId == null || typingDataId.isBlank())
            throw new InvalidArgumentException("Typing data id cannot be empty");

        if (isolateDataId != null && isolateDataId.isBlank())
            throw new InvalidArgumentException("Isolate data id cannot be empty (but can be null).");

        projectMetadataService.assertExists(projectId, userId);

        try {
            typingDataMetadataService.assertExists(projectId, typingDataId, userId);
        } catch (TypingDataNotFoundException e) {
            throw new TypingDataDoesNotExistException();
        }

        if (isolateDataId != null) {
            try {
                isolateDataMetadataService.assertExists(projectId, isolateDataId, userId);
            } catch (IsolateDataNotFoundException e) {
                throw new IsolateDataDoesNotExistException();
            }
        }
    }
}
