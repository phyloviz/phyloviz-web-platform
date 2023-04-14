package org.phyloviz.pwp.administration.service.dtos.datasets;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.distanceMatrices.DistanceMatrixDTO;
import org.phyloviz.pwp.administration.service.dtos.treeViews.TreeViewDTO;
import org.phyloviz.pwp.administration.service.dtos.trees.TreeDTO;

import java.util.List;

@Data
public class DatasetDTO {
    private final String datasetId;
    private final String name;
    private final String description;
    private final String typingDataId;
    private final String isolateDataId;
    private final List<DistanceMatrixDTO> distanceMatrices;
    private final List<TreeDTO> trees;
    private final List<TreeViewDTO> treeViews;
}
