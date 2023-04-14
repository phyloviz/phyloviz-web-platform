package org.phyloviz.pwp.visualization.service.dtos.getTreeView;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;


@Data
public class GetTreeViewInputDTO {
    private final String projectId;
    private final String datasetId;
    private final String treeViewId;
    private final UserDTO user;
}
