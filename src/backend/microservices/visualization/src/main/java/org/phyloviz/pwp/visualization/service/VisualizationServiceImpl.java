package org.phyloviz.pwp.visualization.service;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.DistanceMatrixDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.distance_matrix.repository.DistanceMatrixDataRepository;
import org.phyloviz.pwp.shared.repository.data.registry.distance_matrix.DistanceMatrixDataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.data.registry.tree.TreeDataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.data.registry.tree_view.TreeViewDataRepositoryFactory;
import org.phyloviz.pwp.shared.repository.data.tree.TreeDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.tree.repository.TreeDataRepository;
import org.phyloviz.pwp.shared.repository.data.tree_view.TreeViewDataRepositoryId;
import org.phyloviz.pwp.shared.repository.data.tree_view.repository.TreeViewDataRepository;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.DistanceMatrixMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;
import org.phyloviz.pwp.shared.repository.metadata.project.ProjectRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree.TreeMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.TreeViewMetadataRepository;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataRowsOutput;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataProfilesOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.tree_view.GetTreeViewOutput;
import org.phyloviz.pwp.shared.service.exceptions.DistanceMatrixNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.ProjectNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TreeNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TreeViewNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisualizationServiceImpl implements VisualizationService {

    private final ProjectRepository projectRepository;

    private final DistanceMatrixMetadataRepository distanceMatrixMetadataRepository;
    private final TreeMetadataRepository treeMetadataRepository;
    private final TreeViewMetadataRepository treeViewMetadataRepository;

    private final DistanceMatrixDataRepositoryFactory distanceMatrixDataRepositoryFactory;
    private final TreeDataRepositoryFactory treeDataRepositoryFactory;
    private final TreeViewDataRepositoryFactory treeViewDataRepositoryFactory;

    @Value("${data-repositories.get-distance-matrix-repository}")
    private DistanceMatrixDataRepositoryId getDistanceMatrixRepositoryId;

    @Value("${data-repositories.get-tree-repository}")
    private TreeDataRepositoryId getTreeRepositoryId;

    @Value("${data-repositories.get-tree-view-repository}")
    private TreeViewDataRepositoryId getTreeViewRepositoryId;

    @Override
    public String getDistanceMatrix(String projectId, String datasetId, String distanceMatrixId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        DistanceMatrixMetadata distanceMatrix =
                distanceMatrixMetadataRepository.findByProjectIdAndDatasetIdAndDistanceMatrixIdAndRepositoryId(
                        projectId, datasetId, distanceMatrixId, getDistanceMatrixRepositoryId
                ).orElseThrow(DistanceMatrixNotFoundException::new);

        DistanceMatrixDataRepository distanceMatrixDataRepository = distanceMatrixDataRepositoryFactory
                .getRepository(distanceMatrix.getRepositoryId());

        return distanceMatrixDataRepository.getDistanceMatrix(distanceMatrix.getRepositorySpecificData());
    }

    @Override
    public String getTree(String projectId, String datasetId, String treeId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        TreeMetadata treeMetadata =
                treeMetadataRepository.findByProjectIdAndDatasetIdAndTreeViewIdAndRepositoryId(
                        projectId, datasetId, treeId, getTreeRepositoryId
                ).orElseThrow(TreeNotFoundException::new);

        TreeDataRepository treeDataRepository = treeDataRepositoryFactory.getRepository(treeMetadata.getRepositoryId());

        return treeDataRepository.getTree(treeMetadata.getRepositorySpecificData());

        //throw new TreeIndexingNeededException("Tree isn't indexed in the database. Needs indexing to get it."); TODO check this "indexing concept"
    }

    @Override
    public GetTreeViewOutput getTreeView(String projectId, String datasetId, String treeViewId, String userId) {
        if (!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        TreeViewMetadata treeViewMetadata =
                treeViewMetadataRepository.findByProjectIdAndDatasetIdAndTreeViewIdAndRepositoryId(
                        projectId, datasetId, treeViewId, getTreeViewRepositoryId
                ).orElseThrow(TreeViewNotFoundException::new);

        TreeViewDataRepository treeViewDataRepository = treeViewDataRepositoryFactory.getRepository(treeViewMetadata.getRepositoryId());

        return treeViewDataRepository.getTreeView(treeViewMetadata.getRepositorySpecificData());
    }

    @Override
    public GetTypingDataSchemaOutput getTypingDataSchema(String projectId, String typingDataId, String userId) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public GetTypingDataProfilesOutput getTypingDataProfiles(String projectId, String typingDataId, int limit, int offset, String userId) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public GetIsolateDataSchemaOutput getIsolateDataSchema(String projectId, String isolateDataId, String userId) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public GetIsolateDataRowsOutput getIsolateDataRows(String projectId, String isolateDataId, int limit, int offset, String userId) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
