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
import org.phyloviz.pwp.shared.repository.metadata.distance_matrix.documents.DistanceMatrixMetadata;
import org.phyloviz.pwp.shared.repository.metadata.tree.documents.TreeMetadata;
import org.phyloviz.pwp.shared.repository.metadata.tree_view.documents.TreeViewMetadata;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataRowsOutput;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataProfilesOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.tree_view.GetTreeViewOutput;
import org.phyloviz.pwp.shared.service.exceptions.DistanceMatrixNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TreeNotFoundException;
import org.phyloviz.pwp.shared.service.exceptions.TreeViewNotFoundException;
import org.phyloviz.pwp.shared.service.project.dataset.distance_matrix.DistanceMatrixMetadataService;
import org.phyloviz.pwp.shared.service.project.dataset.tree.TreeMetadataService;
import org.phyloviz.pwp.shared.service.project.dataset.tree_view.TreeViewMetadataService;
import org.phyloviz.pwp.shared.service.project.file.isolate_data.IsolateDataMetadataService;
import org.phyloviz.pwp.shared.service.project.file.typing_data.TypingDataMetadataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisualizationServiceImpl implements VisualizationService {

    private final DistanceMatrixMetadataService distanceMatrixMetadataService;
    private final TreeMetadataService treeMetadataService;
    private final TreeViewMetadataService treeViewMetadataService;
    private final TypingDataMetadataService typingDataMetadataService;
    private final IsolateDataMetadataService isolateDataMetadataService;

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
        distanceMatrixMetadataService.assertExists(projectId, datasetId, distanceMatrixId, userId);

        DistanceMatrixMetadata distanceMatrix =
                distanceMatrixMetadataService.getDistanceMatrixMetadataByAdapterIdOrNull(distanceMatrixId, getDistanceMatrixAdapter);

        if (distanceMatrix == null)
            throw new DistanceMatrixNotFoundException();

        DistanceMatrixAdapter distanceMatrixAdapter = distanceMatrixAdapterFactory
                .getDistanceMatrixAdapter(distanceMatrix.getAdapterId());

        return distanceMatrixAdapter.getDistanceMatrix(distanceMatrix.getAdapterSpecificData());
    }

    @Override
    public String getTree(String projectId, String datasetId, String treeId, String userId) {
        treeMetadataService.assertExists(projectId, datasetId, treeId, userId);

        TreeMetadata treeMetadata = treeMetadataService.getTreeMetadataByAdapterIdOrNull(treeId, getTreeAdapter);

        if (treeMetadata == null)
            throw new TreeNotFoundException();

        TreeAdapter treeAdapter = treeAdapterFactory.getTreeAdapter(treeMetadata.getAdapterId());

        return treeAdapter.getTree(treeMetadata.getAdapterSpecificData());

        //throw new TreeIndexingNeededException("Tree isn't indexed. Needs indexing to get it."); TODO check this "indexing concept"
    }

    @Override
    public GetTreeViewOutput getTreeView(String projectId, String datasetId, String treeViewId, String userId) {
        treeViewMetadataService.assertExists(projectId, datasetId, treeViewId, userId);

        TreeViewMetadata treeViewMetadata =
                treeViewMetadataService.getTreeViewMetadataByAdapterIdOrNull(treeViewId, getTreeViewAdapter);

        if (treeViewMetadata == null)
            throw new TreeViewNotFoundException();

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
