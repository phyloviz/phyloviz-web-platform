package org.phyloviz.pwp.visualization.http.controllers.models.getTreeView;

import lombok.Data;
import org.phyloviz.pwp.visualization.service.dtos.getTreeView.GetTreeViewOutput;

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
