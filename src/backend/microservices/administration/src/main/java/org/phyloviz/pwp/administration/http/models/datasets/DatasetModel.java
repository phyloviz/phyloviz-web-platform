package org.phyloviz.pwp.administration.http.models.datasets;

import lombok.Data;
import org.phyloviz.pwp.administration.http.models.distanceMatrices.DistanceMatrixOutputModel;
import org.phyloviz.pwp.administration.http.models.treeViews.TreeViewOutputModel;
import org.phyloviz.pwp.administration.http.models.trees.TreeOutputModel;
import org.phyloviz.pwp.shared.service.dtos.dataset.DatasetDTO;

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

    public DatasetModel(DatasetDTO datasetDTO) {
        this.datasetId = datasetDTO.getDatasetId();
        this.name = datasetDTO.getName();
        this.description = datasetDTO.getDescription();
        this.typingDataId = datasetDTO.getTypingDataId();
        this.isolateDataId = datasetDTO.getIsolateDataId();
        this.distanceMatrices = datasetDTO.getDistanceMatrices().stream().map(DistanceMatrixOutputModel::new).toList();
        this.trees = datasetDTO.getTrees().stream().map(TreeOutputModel::new).toList();
        this.treeViews = datasetDTO.getTreeViews().stream().map(TreeViewOutputModel::new).toList();
    }
}
