package org.phyloviz.pwp.visualization.http.controllers.models.getTreeView;

import lombok.Data;
import org.phyloviz.pwp.visualization.service.dtos.getTreeView.GetTreeViewOutputDTO;

import java.util.Arrays;
import java.util.List;

/**
 * Output model for the get tree view endpoint.
 */
@Data
public class GetTreeViewOutputModel {
    private List<NodeModel> nodes;
    private int totalCount;

    public GetTreeViewOutputModel(GetTreeViewOutputDTO getTreeViewOutputDTO) {
        this.nodes = getTreeViewOutputDTO.getNodes().stream().map(NodeModel::new).toList();
        this.totalCount = getTreeViewOutputDTO.getTotalCount();
    }
}
