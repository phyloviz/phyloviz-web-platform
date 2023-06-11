package org.phyloviz.pwp.shared.service.dtos.tree_view;

import lombok.Data;


/**
 * Transformations for the Tree View.
 */
@Data
public class Transformations {
    private final double linkSpring;
    private final double linkDistance;
    private final double gravity;
    private final double repulsion;
    private final double friction;
    private final double repulsionTheta;
    private final double decay;
    private final double nodeSize;
    private final boolean nodeLabel;
    private final double nodeLabelSize;
    private final double linkWidth;
    private final boolean linkLabel;
    private final double linkLabelSize;
    private final String linkLabelType;
}
