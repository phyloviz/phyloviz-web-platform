package org.phyloviz.pwp.shared.adapters.treeView;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.Edge;
import org.phyloviz.pwp.shared.service.dtos.Node;

import java.util.List;

@Data
public class AdapterGetTreeViewDTO {
    private final List<Node> nodes;
    private final List<Edge> edges;
}
