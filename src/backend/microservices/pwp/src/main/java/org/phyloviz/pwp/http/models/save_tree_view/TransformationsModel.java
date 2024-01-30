package org.phyloviz.pwp.http.models.save_tree_view;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.phyloviz.pwp.shared.service.dtos.tree_view.Transformations;

/**
 * Transformations model for the Tree View.
 */
@Data
@NoArgsConstructor
public class TransformationsModel {
    private double linkSpring;
    private double linkDistance;
    private double gravity;
    private double repulsion;
    private double friction;
    private double repulsionTheta;
    private double decay;
    private double nodeSize;
    private boolean nodeLabel;
    private double nodeLabelSize;
    private double linkWidth;
    private boolean linkLabel;
    private double linkLabelSize;
    private String linkLabelType;

    public TransformationsModel(Transformations transformations) {
        this.linkSpring = transformations.getLinkSpring();
        this.linkDistance = transformations.getLinkDistance();
        this.gravity = transformations.getGravity();
        this.repulsion = transformations.getRepulsion();
        this.friction = transformations.getFriction();
        this.repulsionTheta = transformations.getRepulsionTheta();
        this.decay = transformations.getDecay();
        this.nodeSize = transformations.getNodeSize();
        this.nodeLabel = transformations.isNodeLabel();
        this.nodeLabelSize = transformations.getNodeLabelSize();
        this.linkWidth = transformations.getLinkWidth();
        this.linkLabel = transformations.isLinkLabel();
        this.linkLabelSize = transformations.getLinkLabelSize();
        this.linkLabelType = transformations.getLinkLabelType();
    }

    public Transformations toDto() {
        return new Transformations(linkSpring, linkDistance, gravity, repulsion, friction, repulsionTheta, decay,
                nodeSize, nodeLabel, nodeLabelSize, linkWidth, linkLabel, linkLabelSize, linkLabelType);
    }
}