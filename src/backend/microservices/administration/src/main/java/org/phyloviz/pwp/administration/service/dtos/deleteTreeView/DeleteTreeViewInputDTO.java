package org.phyloviz.pwp.administration.service.dtos.deleteTreeView;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;

@Data
public class DeleteTreeViewInputDTO {
    private final String treeViewId;
    private final UserDTO user;
}
