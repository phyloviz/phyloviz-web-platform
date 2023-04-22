package org.phyloviz.pwp.visualization.service;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.adapters.distance_matrix.DistanceMatrixAdapterFactory;
import org.phyloviz.pwp.shared.adapters.distance_matrix.DistanceMatrixAdapterId;
import org.phyloviz.pwp.shared.adapters.distance_matrix.adapter.DistanceMatrixAdapter;
import org.phyloviz.pwp.shared.adapters.tree.TreeAdapterFactory;
import org.phyloviz.pwp.shared.adapters.tree.TreeAdapterId;
import org.phyloviz.pwp.shared.adapters.tree.adapter.TreeAdapter;
import org.phyloviz.pwp.shared.adapters.tree_view.TreeViewAdapterFactory;
import org.phyloviz.pwp.shared.adapters.tree_view.TreeViewAdapterId;
import org.phyloviz.pwp.shared.adapters.tree_view.adapter.TreeViewAdapter;
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

    private final DistanceMatrixAdapterFactory distanceMatrixAdapterFactory;
    private final TreeAdapterFactory treeAdapterFactory;
    private final TreeViewAdapterFactory treeViewAdapterFactory;

    @Value("${adapters.get-distance-matrix-adapter}")
    private DistanceMatrixAdapterId getDistanceMatrixAdapter;

    @Value("${adapters.get-tree-adapter}")
    private TreeAdapterId getTreeAdapter;

    @Value("${adapters.get-tree-view-adapter}")
    private TreeViewAdapterId getTreeViewAdapter;

    @Override
    public String getDistanceMatrix(String projectId, String datasetId, String distanceMatrixId, String userId) {
        if(!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        DistanceMatrixMetadata distanceMatrix =
                distanceMatrixMetadataRepository.findByProjectIdAndDatasetIdAndDistanceMatrixIdAndAdapterId(
                        projectId, datasetId, distanceMatrixId, getDistanceMatrixAdapter
                ).orElseThrow(DistanceMatrixNotFoundException::new);

        DistanceMatrixAdapter distanceMatrixAdapter = distanceMatrixAdapterFactory
                .getDistanceMatrixAdapter(distanceMatrix.getAdapterId());

        return distanceMatrixAdapter.getDistanceMatrix(distanceMatrix.getAdapterSpecificData());
    }

    @Override
    public String getTree(String projectId, String datasetId, String treeId, String userId) {
        if(!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        TreeMetadata treeMetadata =
                treeMetadataRepository.findByProjectIdAndDatasetIdAndTreeViewIdAndAdapterId(
                        projectId, datasetId, treeId, getTreeAdapter
                ).orElseThrow(TreeNotFoundException::new);

        TreeAdapter treeAdapter = treeAdapterFactory.getTreeAdapter(treeMetadata.getAdapterId());

        return treeAdapter.getTree(treeMetadata.getAdapterSpecificData());

        //throw new TreeIndexingNeededException("Tree isn't indexed in the database. Needs indexing to get it."); TODO check this "indexing concept"
    }

    @Override
    public GetTreeViewOutput getTreeView(String projectId, String datasetId, String treeViewId, String userId) {
        if(!projectRepository.existsByIdAndOwnerId(projectId, userId))
            throw new ProjectNotFoundException();

        TreeViewMetadata treeViewMetadata =
                treeViewMetadataRepository.findByProjectIdAndDatasetIdAndTreeViewIdAndAdapterId(
                        projectId, datasetId, treeViewId, getTreeViewAdapter
                ).orElseThrow(TreeViewNotFoundException::new);

        TreeViewAdapter treeViewAdapter = treeViewAdapterFactory.getTreeViewAdapter(treeViewMetadata.getAdapterId());

        return treeViewAdapter.getTreeView(treeViewMetadata.getAdapterSpecificData());
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
