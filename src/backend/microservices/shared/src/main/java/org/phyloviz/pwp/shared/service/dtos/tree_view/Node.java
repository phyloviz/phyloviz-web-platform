package org.phyloviz.pwp.shared.service.dtos.tree_view;

import lombok.Data;

import java.util.List;

/**
 * DTO for a tree node.
 */
@Data
public class Node {
    private final int st;
    private final double[] coordinates;
    private final List<String> profile;
    private final Object auxiliaryData; // TODO: See how this will be
}
