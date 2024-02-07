package org.phyloviz.pwp.service.dtos.tree_view;

import lombok.Data;

import java.util.List;

/**
 * Input for the saveTreeView service.
 */
@Data
public class SaveTreeViewInput {
    private final List<Node> nodes;
    private final Transformations transformations;
}
