package org.phyloviz.pwp.visualization.service.dtos.getTreeView;

import lombok.Data;

import java.util.List;

/**
 * Output DTO for the getTreeView service.
 */
@Data
public class GetTreeViewOutputDTO {
    private final List<NodeDTO> nodes;
    private final int totalCount;
}
