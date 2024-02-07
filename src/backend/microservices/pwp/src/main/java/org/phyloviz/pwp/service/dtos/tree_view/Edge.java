package org.phyloviz.pwp.service.dtos.tree_view;

import lombok.Data;

@Data
public class Edge {
    private final String from;
    private final String to;
    private final long weight;
}
