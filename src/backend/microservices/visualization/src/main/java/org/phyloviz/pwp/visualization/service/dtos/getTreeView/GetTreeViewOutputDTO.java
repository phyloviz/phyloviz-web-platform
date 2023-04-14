package org.phyloviz.pwp.visualization.service.dtos.getTreeView;

import lombok.Data;
import org.phyloviz.pwp.shared.adapters.treeView.AdapterGetTreeViewDTO;
import org.phyloviz.pwp.shared.service.dtos.NodeDTO;

import java.util.List;

/**
 * Output DTO for the getTreeView service.
 */
@Data
public class GetTreeViewOutputDTO {
    private final List<NodeDTO> nodes;
    private final int totalCount;

    public GetTreeViewOutputDTO(AdapterGetTreeViewDTO adapterGetTreeViewDTO) {
        this.nodes = adapterGetTreeViewDTO.getNodes();
        this.totalCount = adapterGetTreeViewDTO.getTotalCount();
    }
}
