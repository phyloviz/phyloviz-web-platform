package org.phyloviz.pwp.visualization.http.models.get_tree_view;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.phyloviz.pwp.shared.service.dtos.tree_view.Node;

import java.util.List;

/**
 * Model for a tree node.
 */
@Data
@NoArgsConstructor
public class NodeModel {
    private String st;
    private double[] coordinates;
    private List<String> profile;

    public NodeModel(Node node) {
        this.st = node.getSt();
        this.coordinates = node.getCoordinates();
        this.profile = node.getProfile();
    }

    public Node toDto() {
        return new Node(st, coordinates, profile);
    }
}
