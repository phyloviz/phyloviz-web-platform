package org.phyloviz.pwp.visualization.service.dtos.getTree;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;


@Data
public class GetTreeInputDTO {
    private final String projectId;
    private final String datasetId;
    private final String treeId;
    private final UserDTO user;
}
