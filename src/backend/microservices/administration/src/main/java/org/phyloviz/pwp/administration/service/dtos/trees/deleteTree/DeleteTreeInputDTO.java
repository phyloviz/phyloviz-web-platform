package org.phyloviz.pwp.administration.service.dtos.trees.deleteTree;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;

@Data
public class DeleteTreeInputDTO {
    private final String projectId;
    private final String datasetId;
    private final String treeId;
    private final UserDTO user;
}
