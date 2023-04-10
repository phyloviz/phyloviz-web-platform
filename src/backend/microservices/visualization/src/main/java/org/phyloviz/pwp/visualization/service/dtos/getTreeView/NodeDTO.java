package org.phyloviz.pwp.visualization.service.dtos.getTreeView;

import lombok.Data;

import java.util.List;

/**
 * DTO for a tree node.
 */
@Data
public class NodeDTO {
    private final int st;
    private final int[] coordinates;
    private final List<String> profile;
    private final Object auxiliaryData; // TODO: See how this will be
}
