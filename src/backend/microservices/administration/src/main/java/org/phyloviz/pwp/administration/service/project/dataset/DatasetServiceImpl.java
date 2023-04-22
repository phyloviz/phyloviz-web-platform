package org.phyloviz.pwp.administration.service.project.dataset;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.service.project.dataset.distance_matrix.DistanceMatrixService;
import org.phyloviz.pwp.administration.service.project.dataset.tree.TreeService;
import org.phyloviz.pwp.administration.service.project.dataset.tree_view.TreeViewService;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.IsolateDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.TypingDataMetadataRepository;
import org.phyloviz.pwp.shared.service.dtos.dataset.CreateDatasetOutput;
import org.phyloviz.pwp.shared.service.dtos.dataset.FullDatasetInfo;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.shared.service.exceptions.IsolateDataDoesNotExistException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TypingDataDoesNotExistException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DatasetServiceImpl implements DatasetService {

    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;
    private final TypingDataMetadataRepository typingDataMetadataRepository;
    private final IsolateDataMetadataRepository isolateDataMetadataRepository;

    private final DistanceMatrixService distanceMatrixService;
    private final TreeService treeService;
    private final TreeViewService treeViewService;

    @Override
    public CreateDatasetOutput createDataset(String name, String description, String typingDataId, String isolateDataId,
                                             String projectId, String userId) {
        validateCreateDatasetParameters(name, typingDataId, isolateDataId, projectId, userId);

        Dataset dataset = new Dataset(
                projectId,
                name,
                description,
                typingDataId,
                isolateDataId
        );

        Dataset storedDataset = datasetRepository.save(dataset);
        return new CreateDatasetOutput(projectId, storedDataset.getId());
    }

    @Override
    public FullDatasetInfo getFullDatasetInfo(String projectId, String datasetId, String userId) {
        return getFullDatasetInfo(datasetRepository.findByProjectIdAndId(projectId, datasetId)
                .orElseThrow(DatasetNotFoundException::new));
    }

    @Override
    public List<FullDatasetInfo> getFullDatasetInfos(String projectId, String userId) {
        if(!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        return getFullDatasetInfos(projectId);
    }

    @Override
    public List<FullDatasetInfo> getFullDatasetInfos(String projectId) {
        return datasetRepository.findAllByProjectId(projectId)
                .stream().map(this::getFullDatasetInfo).toList();
    }

    @Override
    public void deleteDataset(String projectId, String datasetId, String userId) {
        if(!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        Dataset dataset = datasetRepository.findByProjectIdAndId(projectId, datasetId)
                .orElseThrow(DatasetNotFoundException::new);

        deleteAllChildrenByProjectIdAndDatasetId(projectId, datasetId);

        datasetRepository.delete(dataset);
    }

    @Override
    public void deleteAllByProjectId(String projectId) {
        datasetRepository.findAllByProjectId(projectId).forEach(dataset -> {
            deleteAllChildrenByProjectIdAndDatasetId(projectId, dataset.getId());
            datasetRepository.delete(dataset);
        });
    }

    private void deleteAllChildrenByProjectIdAndDatasetId(String projectId, String datasetId) {
        treeViewService.deleteAllByProjectIdAndDatasetId(projectId, datasetId);
        treeService.deleteAllByProjectIdAndDatasetId(projectId, datasetId);
        distanceMatrixService.deleteAllByProjectIdAndDatasetId(projectId, datasetId);
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

        if(!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        if (!typingDataMetadataRepository.existsByProjectIdAndTypingDataId(projectId, typingDataId))
            throw new TypingDataDoesNotExistException();

        if (isolateDataId != null && !isolateDataMetadataRepository.existsByProjectIdAndIsolateDataId(projectId, isolateDataId))
            throw new IsolateDataDoesNotExistException();
    }
}
