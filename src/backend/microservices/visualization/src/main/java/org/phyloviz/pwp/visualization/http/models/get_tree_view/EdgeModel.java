package org.phyloviz.pwp.visualization.http.models.get_tree_view;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.tree_view.Edge;

@Data
public class EdgeModel {
    private String from;
    private String to;
    private long weight;

    public EdgeModel(Edge edge) {
        this.from = edge.getFrom();
        this.to = edge.getTo();
        this.weight = edge.getWeight();
    }
}
