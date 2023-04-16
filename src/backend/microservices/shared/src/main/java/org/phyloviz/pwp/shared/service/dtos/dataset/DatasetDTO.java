package org.phyloviz.pwp.shared.service.dtos.dataset;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.distanceMatrix.DistanceMatrixMetadataDTO;
import org.phyloviz.pwp.shared.service.dtos.tree.TreeMetadataDTO;
import org.phyloviz.pwp.shared.service.dtos.treeView.TreeViewMetadataDTO;

import java.util.List;

@Data
public class DatasetDTO {
    private final String datasetId;
    private final String name;
    private final String description;
    private final String typingDataId;
    private final String isolateDataId;
    private final List<DistanceMatrixMetadataDTO> distanceMatrices;
    private final List<TreeMetadataDTO> trees;
    private final List<TreeViewMetadataDTO> treeViews;
}
