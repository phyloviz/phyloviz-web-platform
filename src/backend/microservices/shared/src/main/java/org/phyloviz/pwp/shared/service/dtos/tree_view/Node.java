package org.phyloviz.pwp.shared.service.dtos.tree_view;

import lombok.Data;

import java.util.List;

/**
 * Node on a tree view.
 */
@Data
public class Node {
    private final String st;
    private final double[] coordinates;
    private final List<String> profile;
}
