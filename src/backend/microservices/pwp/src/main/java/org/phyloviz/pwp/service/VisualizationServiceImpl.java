package org.phyloviz.pwp.service;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.service.exceptions.IndexingNeededException;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.DistanceMatrixDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.DistanceMatrixDataRepository;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.specific_data.DistanceMatrixDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.isolate_data.IsolateDataDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.IsolateDataDataRepository;
import org.phyloviz.pwp.shared.repository.data.isolate_data.repository.specific_data.IsolateDataDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.registry.distance_matrix.DistanceMatrixDataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.data.registry.isolate_data.IsolateDataDataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.data.registry.tree.TreeDataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.data.registry.tree_view.TreeViewDataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.data.registry.typing_data.TypingDataDataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.data.tree.TreeDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.tree.repository.TreeDataRepository;
import org.phyloviz.pwp.shared.repository.data.tree.repository.specific_data.TreeDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.tree_view.TreeViewDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.TreeViewDataRepository;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.specific_data.TreeViewDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.data.typing_data.TypingDataDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.typing_data.repository.TypingDataDataRepository;
import org.phyloviz.pwp.shared.repository.data.typing_data.repository.specific_data.TypingDataDataRepositorySpecificData;
import org.phyloviz.pwp.shared.repository.metadata.dataset.DatasetRepository;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.DistanceMatrixMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.IsolateDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree.TreeMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.TreeViewMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.TypingDataMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.typing_data.documents.TypingDataMetadata;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataRowsOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataProfilesOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.tree_view.GetTreeViewOutput;
import org.phyloviz.pwp.shared.service.dtos.tree_view.SaveTreeViewInput;
import org.phyloviz.pwp.shared.service.dtos.tree_view.SaveTreeViewOutput;
import org.phyloviz.pwp.shared.service.exceptions.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VisualizationServiceImpl implements VisualizationService {

    private final ProjectRepository projectRepository;
    private final DatasetRepository datasetRepository;

    private final DistanceMatrixMetadataRepository distanceMatrixMetadataRepository;
    private final TreeMetadataRepository treeMetadataRepository;
    private final TreeViewMetadataRepository treeViewMetadataRepository;
    private final TypingDataMetadataRepository typingDataMetadataRepository;
    private final IsolateDataMetadataRepository isolateDataMetadataRepository;

    private final DistanceMatrixDataRepositoryFactory distanceMatrixDataRepositoryFactory;
    private final TreeDataRepositoryFactory treeDataRepositoryFactory;
    private final TreeViewDataRepositoryFactory treeViewDataRepositoryFactory;
    private final TypingDataDataRepositoryFactory typingDataDataRepositoryFactory;
    private final IsolateDataDataRepositoryFactory isolateDataDataRepositoryFactory;

    @Value("${data-repositories.get-distance-matrix-repository}")
    private DistanceMatrixDataRepositoryId getDistanceMatrixRepositoryId;

    @Value("${data-repositories.get-tree-repository}")
    private TreeDataRepositoryId getTreeRepositoryId;

    @Value("${data-repositories.get-tree-view-repository}")
    private TreeViewDataRepositoryId getTreeViewRepositoryId;

    @Value("${data-repositories.get-typing-data-repository}")
    private TypingDataDataRepositoryId getTypingDataRepositoryId;

    @Value("${data-repositories.get-isolate-data-repository}")
    private IsolateDataDataRepositoryId getIsolateDataRepositoryId;

    @Override
    public String getDistanceMatrix(String projectId, String datasetId, String distanceMatrixId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        if (!datasetRepository.existsByProjectIdAndId(projectId, datasetId))
            throw new DatasetNotFoundException();

        DistanceMatrixMetadata distanceMatrix = distanceMatrixMetadataRepository
                .findByProjectIdAndDatasetIdAndDistanceMatrixId(projectId, datasetId, distanceMatrixId)
                .orElseThrow(DistanceMatrixNotFoundException::new);

        if (!distanceMatrix.getRepositorySpecificData().containsKey(getDistanceMatrixRepositoryId))
            throw new IndexingNeededException("Distance Matrix isn't indexed in the database. Indexing of Distance Matrix required.");

        DistanceMatrixDataRepositorySpecificData repositorySpecificData = distanceMatrix
                .getRepositorySpecificData()
                .get(getDistanceMatrixRepositoryId);

        DistanceMatrixDataRepository distanceMatrixDataRepository = distanceMatrixDataRepositoryFactory
                .getRepository(getDistanceMatrixRepositoryId);

        return distanceMatrixDataRepository.getDistanceMatrix(repositorySpecificData);
    }

    @Override
    public String getTree(String projectId, String datasetId, String treeId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        if (!datasetRepository.existsByProjectIdAndId(projectId, datasetId))
            throw new DatasetNotFoundException();

        TreeMetadata treeMetadata = treeMetadataRepository
                .findByProjectIdAndDatasetIdAndTreeId(projectId, datasetId, treeId)
                .orElseThrow(TreeNotFoundException::new);

        if (!treeMetadata.getRepositorySpecificData().containsKey(getTreeRepositoryId))
            throw new IndexingNeededException("Tree isn't indexed in the database. Indexing of Tree required.");

        TreeDataRepositorySpecificData repositorySpecificData = treeMetadata
                .getRepositorySpecificData()
                .get(getTreeRepositoryId);

        TreeDataRepository treeDataRepository = treeDataRepositoryFactory
                .getRepository(getTreeRepositoryId);

        return treeDataRepository.getTree(repositorySpecificData);
    }

    @Override
    public GetTreeViewOutput getTreeView(String projectId, String datasetId, String treeViewId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        if (!datasetRepository.existsByProjectIdAndId(projectId, datasetId))
            throw new DatasetNotFoundException();

        TreeViewMetadata treeViewMetadata = treeViewMetadataRepository
                .findByProjectIdAndDatasetIdAndTreeViewId(projectId, datasetId, treeViewId)
                .orElseThrow(TreeViewNotFoundException::new);

        if (!treeViewMetadata.getRepositorySpecificData().containsKey(getTreeViewRepositoryId))
            throw new IndexingNeededException("Tree View isn't indexed in the database. Indexing of Tree View required.");

        TreeViewDataRepositorySpecificData repositorySpecificData = treeViewMetadata
                .getRepositorySpecificData()
                .get(getTreeViewRepositoryId);

        TreeViewDataRepository treeViewDataRepository = treeViewDataRepositoryFactory.getRepository(getTreeViewRepositoryId);

        GetTreeViewOutput output = treeViewDataRepository.getTreeView(repositorySpecificData);

        return new GetTreeViewOutput(
                output.getNodes(),
                output.getNodesTotalCount(),
                output.getEdges(),
                output.getEdgesTotalCount(),
                treeViewMetadata.getTransformations()
        );
    }

    @Override
    public SaveTreeViewOutput saveTreeView(String projectId, String datasetId, String treeViewId, SaveTreeViewInput inputModel, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        if (!datasetRepository.existsByProjectIdAndId(projectId, datasetId))
            throw new DatasetNotFoundException();

        TreeViewMetadata treeViewMetadata = treeViewMetadataRepository
                .findByProjectIdAndDatasetIdAndTreeViewId(projectId, datasetId, treeViewId)
                .orElseThrow(TreeViewNotFoundException::new);

        if (!treeViewMetadata.getRepositorySpecificData().containsKey(getTreeViewRepositoryId))
            throw new IndexingNeededException("Tree View isn't indexed in the database. Indexing of Tree View required.");

        TreeViewDataRepositorySpecificData repositorySpecificData = treeViewMetadata
                .getRepositorySpecificData()
                .get(getTreeViewRepositoryId);

        TreeViewDataRepository treeViewDataRepository = treeViewDataRepositoryFactory.getRepository(getTreeViewRepositoryId);

        treeViewMetadata.setTransformations(inputModel.getTransformations());
        treeViewMetadataRepository.save(treeViewMetadata);

        treeViewDataRepository.saveTreeView(repositorySpecificData, inputModel.getNodes());

        return new SaveTreeViewOutput(projectId, datasetId, treeViewId);
    }

    @Override
    public GetTypingDataSchemaOutput getTypingDataSchema(String projectId, String typingDataId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        TypingDataMetadata typingDataMetadata = typingDataMetadataRepository
                .findByProjectIdAndTypingDataId(projectId, typingDataId)
                .orElseThrow(TypingDataNotFoundException::new);

        if (!typingDataMetadata.getRepositorySpecificData().containsKey(getTypingDataRepositoryId))
            throw new IndexingNeededException("Typing Data isn't indexed in the database. Indexing of Typing Data required.");

        TypingDataDataRepositorySpecificData repositorySpecificData = typingDataMetadata
                .getRepositorySpecificData()
                .get(getTypingDataRepositoryId);

        TypingDataDataRepository typingDataDataRepository = typingDataDataRepositoryFactory.getRepository(getTypingDataRepositoryId);

        return typingDataDataRepository.getTypingDataSchema(repositorySpecificData);
    }

    @Override
    public GetTypingDataProfilesOutput getTypingDataProfiles(String projectId, String typingDataId, int limit, int offset, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        TypingDataMetadata typingDataMetadata = typingDataMetadataRepository
                .findByProjectIdAndTypingDataId(projectId, typingDataId)
                .orElseThrow(TypingDataNotFoundException::new);

        if (!typingDataMetadata.getRepositorySpecificData().containsKey(getTypingDataRepositoryId))
            throw new IndexingNeededException("Typing Data isn't indexed in the database. Indexing of Typing Data required.");

        TypingDataDataRepositorySpecificData repositorySpecificData = typingDataMetadata
                .getRepositorySpecificData()
                .get(getTypingDataRepositoryId);

        TypingDataDataRepository typingDataDataRepository = typingDataDataRepositoryFactory.getRepository(getTypingDataRepositoryId);

        return typingDataDataRepository.getTypingDataProfiles(repositorySpecificData, limit, offset);
    }

    @Override
    public List<String> getIsolateDataKeys(String projectId, String isolateDataId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        IsolateDataMetadata isolateDataMetadata = isolateDataMetadataRepository
                .findByProjectIdAndIsolateDataId(projectId, isolateDataId)
                .orElseThrow(IsolateDataNotFoundException::new);

        return isolateDataMetadata.getKeys();
    }

    @Override
    public GetIsolateDataRowsOutput getIsolateDataRows(String projectId, String isolateDataId, int limit, int offset, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        IsolateDataMetadata isolateDataMetadata = isolateDataMetadataRepository
                .findByProjectIdAndIsolateDataId(projectId, isolateDataId)
                .orElseThrow(IsolateDataNotFoundException::new);

        if (!isolateDataMetadata.getRepositorySpecificData().containsKey(getIsolateDataRepositoryId))
            throw new IndexingNeededException("Isolate Data isn't indexed in the database. Indexing of Isolate Data required.");

        IsolateDataDataRepositorySpecificData repositorySpecificData = isolateDataMetadata
                .getRepositorySpecificData()
                .get(getIsolateDataRepositoryId);

        IsolateDataDataRepository isolateDataDataRepository = isolateDataDataRepositoryFactory.getRepository(getIsolateDataRepositoryId);

        return isolateDataDataRepository.getIsolateDataRows(repositorySpecificData, limit, offset);
    }
}
