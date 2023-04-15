package org.phyloviz.pwp.shared.service.dtos.treeView;

import lombok.Data;

import java.util.List;

/**
 * Output DTO for the getTreeView service.
 */
@Data
public class GetTreeViewOutput {
    private final List<NodeDTO> nodes;
    private final int totalCount;
}