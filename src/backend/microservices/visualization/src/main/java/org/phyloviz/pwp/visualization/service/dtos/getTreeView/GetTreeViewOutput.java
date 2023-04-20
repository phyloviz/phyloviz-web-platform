package org.phyloviz.pwp.visualization.service.dtos.getTreeView;

import lombok.Data;
import org.phyloviz.pwp.shared.adapters.treeView.AdapterGetTreeViewDTO;
import org.phyloviz.pwp.shared.service.dtos.Edge;
import org.phyloviz.pwp.shared.service.dtos.Node;

import java.util.List;

/**
 * Output DTO for the getTreeView service.
 */
@Data
public class GetTreeViewOutput {
    private final List<Node> nodes;
    private final List<Edge> edges;

    public GetTreeViewOutput(AdapterGetTreeViewDTO adapterGetTreeViewDTO) {
        this.nodes = adapterGetTreeViewDTO.getNodes();
        this.edges = adapterGetTreeViewDTO.getEdges();
    }
}
