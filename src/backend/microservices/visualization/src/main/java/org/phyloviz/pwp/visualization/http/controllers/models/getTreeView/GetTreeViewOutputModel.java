package org.phyloviz.pwp.visualization.http.controllers.models.getTreeView;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.treeView.GetTreeViewOutput;

import java.util.List;

/**
 * Output model for the get tree view endpoint.
 */
@Data
public class GetTreeViewOutputModel {
    private List<NodeModel> nodes;
    private int totalCount;

    public GetTreeViewOutputModel(GetTreeViewOutput getTreeViewOutputDTO) {
        this.nodes = getTreeViewOutputDTO.getNodes().stream().map(NodeModel::new).toList();
        this.totalCount = getTreeViewOutputDTO.getTotalCount();
    }
}
