package org.phyloviz.pwp.shared.service.dtos.tree_view;

import lombok.Data;

import java.util.List;

/**
 * Output DTO for the getTreeView service.
 */
@Data
public class GetTreeViewOutput {
    private final List<Node> nodes;
    private final int totalCount;
}