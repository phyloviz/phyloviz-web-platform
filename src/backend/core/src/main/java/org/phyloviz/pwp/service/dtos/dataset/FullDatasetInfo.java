package org.phyloviz.pwp.service.dtos.dataset;

import lombok.Data;
import org.phyloviz.pwp.service.dtos.distance_matrix.DistanceMatrixInfo;
import org.phyloviz.pwp.service.dtos.tree.TreeInfo;
import org.phyloviz.pwp.service.dtos.tree_view.TreeViewInfo;

import java.util.List;

@Data
public class FullDatasetInfo {
    private final String datasetId;
    private final String name;
    private final String description;
    private final String typingDataId;
    private final String isolateDataId;
    private final String isolateDataKey;
    private final List<DistanceMatrixInfo> distanceMatrices;
    private final List<TreeInfo> trees;
    private final List<TreeViewInfo> treeViews;
}
