package org.phyloviz.pwp.visualization.http.controllers.models.get_tree_view;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.tree_view.GetTreeViewOutput;

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
