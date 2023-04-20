package org.phyloviz.pwp.visualization.http.controllers.models.getTreeView;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.Edge;

@Data
public class EdgeModel {
    private String from;
    private String to;

    public EdgeModel(Edge edge) {
        this.from = edge.getFrom();
        this.to = edge.getTo();
    }
}
