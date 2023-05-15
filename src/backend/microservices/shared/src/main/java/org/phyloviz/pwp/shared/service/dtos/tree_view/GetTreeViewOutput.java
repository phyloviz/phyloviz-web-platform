package org.phyloviz.pwp.shared.service.dtos.tree_view;

import lombok.Data;

import java.util.List;

/**
 * Output for the getTreeView service.
 */
@Data
public class GetTreeViewOutput {
    private final List<Node> nodes;
    private final int nodesTotalCount;
    private final List<Edge> edges;
    private final int edgesTotalCount;
}