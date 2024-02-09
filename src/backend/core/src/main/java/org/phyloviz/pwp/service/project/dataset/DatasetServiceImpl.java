package org.phyloviz.pwp.service.project.dataset;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.service.dtos.dataset.CreateDatasetOutput;
import org.phyloviz.pwp.service.dtos.dataset.FullDatasetInfo;
import org.phyloviz.pwp.service.dtos.dataset.SetIsolateDataOfDatasetOutput;
import org.phyloviz.pwp.service.dtos.dataset.UpdateDatasetOutput;
import org.phyloviz.pwp.service.project.dataset.distance_matrix.DistanceMatrixService;
import org.phyloviz.pwp.service.project.dataset.tree.TreeService;
import org.phyloviz.pwp.service.project.dataset.tree_view.TreeViewService;
import org.phyloviz.pwp.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.repository.metadata.dataset.documents.Dataset;
import org.phyloviz.pwp.repository.metadata.isolate_data.IsolateDataMetadataRepository;
import org.phyloviz.pwp.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.phyloviz.pwp.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.repository.metadata.typing_data.TypingDataMetadataRepository;
import org.phyloviz.pwp.service.exceptions.*;
import org.phyloviz.pwp.utils.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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

    @Override
    public SetIsolateDataOfDatasetOutput setIsolateDataOfDataset(String isolateDataId, String isolateDataKey, String projectId, String datasetId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        Dataset dataset = datasetRepository.findByProjectIdAndId(projectId, datasetId)
                .orElseThrow(DatasetNotFoundException::new);

        String previousIsolateDataId = dataset.getIsolateDataId();
        String previousIsolateDataKey = dataset.getIsolateDataKey();

        if (previousIsolateDataId != null || previousIsolateDataKey != null)
            throw new InvalidArgumentException("Dataset already has an isolate data associated");

        if (isolateDataId == null || isolateDataKey == null)
            throw new InvalidArgumentException("You have to provide both isolateDataId and isolateDataKey");

        if (isolateDataId.isBlank() || isolateDataKey.isBlank())
            throw new InvalidArgumentException("IsolateDataId and isolateDataKey can't be blank");

        IsolateDataMetadata isolateDataMetadata =
                isolateDataMetadataRepository.findByProjectIdAndIsolateDataId(projectId, isolateDataId)
                        .orElseThrow(IsolateDataDoesNotExistException::new);

        if (!isolateDataMetadata.getKeys().contains(isolateDataKey))
            throw new InvalidArgumentException(String.format("Isolate data key %s does not exist in isolate data %s",
                    isolateDataKey, isolateDataId
            ));

        dataset.setIsolateDataId(isolateDataId);
        dataset.setIsolateDataKey(isolateDataKey);

        datasetRepository.save(dataset);

        return new SetIsolateDataOfDatasetOutput(isolateDataId, isolateDataKey);
    }

    private void deleteAllChildrenByProjectIdAndDatasetId(String projectId, String datasetId) {
        treeViewService.deleteAllByProjectIdAndDatasetId(projectId, datasetId);
        treeService.deleteAllByProjectIdAndDatasetId(projectId, datasetId);
        distanceMatrixService.deleteAllByProjectIdAndDatasetId(projectId, datasetId);
        // TODO: Delete isolate and typing data phylodb representations
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
                dataset.getIsolateDataKey(),
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
            throw new InvalidArgumentException("Dataset name cannot be blank");
        }

        if (typingDataId == null || typingDataId.isBlank()) {
            throw new InvalidArgumentException("Typing data id cannot be blank");
        }

        if (!UUIDUtils.isValidUUID(typingDataId)) {
            throw new InvalidArgumentException("Typing data id is not a valid UUID.");
        }

        if (isolateDataId != null) {
            if (isolateDataId.isBlank()) {
                throw new InvalidArgumentException("Isolate data id cannot be blank (but can be null).");
            } else if (!UUIDUtils.isValidUUID(isolateDataId)) {
                throw new InvalidArgumentException("Isolate data id is not a valid UUID.");
            }
        }

        if (!typingDataMetadataRepository.existsByProjectIdAndTypingDataId(projectId, typingDataId))
            throw new TypingDataDoesNotExistException();

        if (isolateDataId != null) {
            if (isolateDataKey == null)
                throw new InvalidArgumentException("Isolate data key must not be null. " +
                        "If an isolate data id is specified, an associated key should too."
                );

            IsolateDataMetadata isolateDataMetadata =
                    isolateDataMetadataRepository.findByProjectIdAndIsolateDataId(projectId, isolateDataId)
                            .orElseThrow(IsolateDataDoesNotExistException::new);

            if (!isolateDataMetadata.getKeys().contains(isolateDataKey))
                throw new InvalidArgumentException(String.format("Isolate data key %s does not exist in isolate data %s",
                        isolateDataKey, isolateDataId
                ));
        } else if (isolateDataKey != null) {
            throw new InvalidArgumentException("Isolate data key must be null if isolate data id is null.");
        }
    }
}
