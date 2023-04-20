package org.phyloviz.pwp.visualization.http.controllers.models.getTreeView;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.Node;

import java.util.List;

/**
 * Model for a tree node.
 */
@Data
public class NodeModel {
    private String st;
    private double[] coordinates;
    private List<String> profile;

    public NodeModel(Node node) {
        this.st = node.getSt();
        this.coordinates = node.getCoordinates();
        this.profile = node.getProfile();
    }
}
