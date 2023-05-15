package org.phyloviz.pwp.visualization.http.models.get_tree_view;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.tree_view.GetTreeViewOutput;

import java.util.List;

/**
 * Output model for the get tree view endpoint.
 */
@Data
public class GetTreeViewOutputModel {
    private List<NodeModel> nodes;
    private int nodesTotalCount;
    private List<EdgeModel> edges;
    private int edgesTotalCount;

    public GetTreeViewOutputModel(GetTreeViewOutput getTreeViewOutput) {
        this.nodes = getTreeViewOutput.getNodes().stream().map(NodeModel::new).toList();
        this.nodesTotalCount = getTreeViewOutput.getNodesTotalCount();
        this.edges = getTreeViewOutput.getEdges().stream().map(EdgeModel::new).toList();
        this.edgesTotalCount = getTreeViewOutput.getEdgesTotalCount();
    }
}
