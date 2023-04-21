package org.phyloviz.pwp.administration.http.models.datasets;

import lombok.Data;
import org.phyloviz.pwp.administration.http.models.distance_matrices.DistanceMatrixOutputModel;
import org.phyloviz.pwp.administration.http.models.tree_views.TreeViewOutputModel;
import org.phyloviz.pwp.administration.http.models.trees.TreeOutputModel;
import org.phyloviz.pwp.shared.service.dtos.dataset.FullDatasetInfo;

import java.util.List;

@Data
public class DatasetModel {
    private String datasetId;
    private String name;
    private String description;
    private String typingDataId;
    private String isolateDataId;
    private List<DistanceMatrixOutputModel> distanceMatrices;
    private List<TreeOutputModel> trees;
    private List<TreeViewOutputModel> treeViews;

    public DatasetModel(FullDatasetInfo fullDatasetInfo) {
        this.datasetId = fullDatasetInfo.getDatasetId();
        this.name = fullDatasetInfo.getName();
        this.description = fullDatasetInfo.getDescription();
        this.typingDataId = fullDatasetInfo.getTypingDataId();
        this.isolateDataId = fullDatasetInfo.getIsolateDataId();
        this.distanceMatrices = fullDatasetInfo.getDistanceMatrices().stream().map(DistanceMatrixOutputModel::new).toList();
        this.trees = fullDatasetInfo.getTrees().stream().map(TreeOutputModel::new).toList();
        this.treeViews = fullDatasetInfo.getTreeViews().stream().map(TreeViewOutputModel::new).toList();
    }
}
