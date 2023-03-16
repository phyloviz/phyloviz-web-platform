package org.phyloviz.pwp.visualization.http.controllers.models.getTreeView;

import org.phyloviz.pwp.visualization.service.dtos.getTreeView.GetTreeViewOutputDTO;
import org.phyloviz.pwp.visualization.http.controllers.models.shared.NodeModel;

import java.util.Arrays;

/**
 * Output model for the get tree view endpoint.
 */
public class GetTreeViewOutputModel {
    private final NodeModel[] nodes;
    private final int totalCount;

    public GetTreeViewOutputModel(NodeModel[] nodes, int totalCount) {
        this.nodes = nodes;
        this.totalCount = totalCount;
    }

    public GetTreeViewOutputModel(GetTreeViewOutputDTO getTreeViewOutputDTO) {
        this.nodes = Arrays.stream(getTreeViewOutputDTO.getNodes()).map(NodeModel::new).toArray(NodeModel[]::new);
        this.totalCount = getTreeViewOutputDTO.getTotalCount();
    }
}
