package org.phyloviz.pwp.administration.service.dtos.treeViews.deleteTreeView;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.UserDTO;

@Data
public class DeleteTreeViewInputDTO {
    private final String projectId;
    private final String datasetId;
    private final String treeViewId;
    private final UserDTO user;
}
