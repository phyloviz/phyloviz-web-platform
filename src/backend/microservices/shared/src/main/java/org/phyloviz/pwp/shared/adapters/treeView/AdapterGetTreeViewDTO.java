package org.phyloviz.pwp.shared.adapters.treeView;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.NodeDTO;

import java.util.List;

@Data
public class AdapterGetTreeViewDTO {
    private final List<NodeDTO> nodes;
    private final int totalCount;
}
