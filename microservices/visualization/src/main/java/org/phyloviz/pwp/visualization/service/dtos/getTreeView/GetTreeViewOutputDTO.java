package org.phyloviz.pwp.visualization.service.dtos.getTreeView;

import org.phyloviz.pwp.visualization.service.dtos.shared.NodeDTO;

/**
 * Output DTO for the getTreeView service.
 */
public class GetTreeViewOutputDTO {
    private final NodeDTO[] nodes;
    private final int totalCount;

    public GetTreeViewOutputDTO(NodeDTO[] nodes, int totalCount) {
        this.nodes = nodes;
        this.totalCount = totalCount;
    }

    public NodeDTO[] getNodes() {
        return nodes;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
