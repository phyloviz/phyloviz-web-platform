package org.phyloviz.pwp.administration.service.project.dataset;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.service.dtos.dataset.CreateDatasetOutput;
import org.phyloviz.pwp.administration.service.dtos.dataset.FullDatasetInfo;
import org.phyloviz.pwp.administration.service.dtos.dataset.UpdateDatasetOutput;
import org.phyloviz.pwp.administration.service.project.dataset.distance_matrix.DistanceMatrixService;
import org.phyloviz.pwp.administration.service.project.dataset.tree.TreeService;
import org.phyloviz.pwp.administration.service.project.dataset.tree_view.TreeViewService;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.IsolateDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.TypingDataMetadataRepository;
import org.phyloviz.pwp.shared.service.exceptions.DatasetNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.InvalidArgumentException;
import org.phyloviz.pwp.shared.service.exceptions.IsolateDataDoesNotExistException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TypingDataDoesNotExistException;
import org.phyloviz.pwp.shared.utils.UUIDUtils;
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
                                             String isolateDataKey, String projectId, String userId) {
        validateCreateDatasetParameters(projectId, name, typingDataId, isolateDataId, isolateDataKey, userId);

        Dataset dataset = new Dataset(
                projectId,
                name,
                description,
                typingDataId,
                isolateDataId,
                isolateDataKey
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
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
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
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
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

    @Override
    public UpdateDatasetOutput updateDataset(String name, String description, String projectId, String datasetId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        Dataset dataset = datasetRepository.findByProjectIdAndId(projectId, datasetId)
                .orElseThrow(DatasetNotFoundException::new);

        String previousName = dataset.getName();
        String previousDescription = dataset.getDescription();

        if (name == null && description == null)
            throw new InvalidArgumentException("You have to provide at least one field to update");

        if (name != null && name.isBlank())
            throw new InvalidArgumentException("Name can't be blank");

        if (description != null && description.isBlank())
            throw new InvalidArgumentException("Description can't be blank");

        dataset.setName(name);
        dataset.setDescription(description);

        if (!dataset.getName().equals(previousName) || !dataset.getDescription().equals(previousDescription))
            datasetRepository.save(dataset);

        return new UpdateDatasetOutput(previousName, name, previousDescription, description);
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

    private void validateCreateDatasetParameters(String projectId, String name, String typingDataId, String isolateDataId,
                                                 String isolateDataKey, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        if (name == null || name.isBlank()) {
            throw new InvalidArgumentException("Dataset name cannot be empty");
        }

        if (typingDataId == null || typingDataId.isBlank()) {
            throw new InvalidArgumentException("Typing data id cannot be empty");
        }

        if (!UUIDUtils.isValidUUID(typingDataId)) {
            throw new InvalidArgumentException("Typing data id is not a valid UUID.");
        }

        if (isolateDataId != null) {
            if (isolateDataId.isBlank()) {
                throw new InvalidArgumentException("Isolate data id cannot be empty (but can be null).");
            } else if (!UUIDUtils.isValidUUID(isolateDataId)) {
                throw new InvalidArgumentException("Isolate data id is not a valid UUID.");
            }
        }

        if (!typingDataMetadataRepository.existsByProjectIdAndTypingDataId(projectId, typingDataId))
            throw new TypingDataDoesNotExistException();

        if (isolateDataId != null) {
            IsolateDataMetadata isolateDataMetadata =
                    isolateDataMetadataRepository.findAnyByProjectIdAndIsolateDataId(projectId, isolateDataId)
                            .orElseThrow(IsolateDataDoesNotExistException::new);

            if (isolateDataKey == null)
                throw new InvalidArgumentException("Isolate data key must not be null. " +
                        "If an isolate data id is specified, an associated key should too."
                );
            if (!isolateDataMetadata.getKeys().contains(isolateDataKey))
                throw new InvalidArgumentException(String.format("Isolate data key %s does not exist in isolate data %s",
                        isolateDataKey, isolateDataId
                ));
        }
    }
}
