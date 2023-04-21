package org.phyloviz.pwp.visualization.http.controllers.models.get_tree_view;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.tree_view.GetTreeViewOutput;
import org.phyloviz.pwp.visualization.http.controllers.models.getTreeView.EdgeModel;

import java.util.List;

/**
 * Output model for the get tree view endpoint.
 */
@Data
public class GetTreeViewOutputModel {
    private List<NodeModel> nodes;
    private List<EdgeModel> edges;

    public GetTreeViewOutputModel(GetTreeViewOutput getTreeViewOutput) {
        this.nodes = getTreeViewOutput.getNodes().stream().map(NodeModel::new).toList();
        this.edges = getTreeViewOutput.getEdges().stream().map(EdgeModel::new).toList();
    }
}
