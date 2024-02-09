package org.phyloviz.pwp.http.models.save_tree_view;

import lombok.Data;
import org.phyloviz.pwp.http.models.get_tree_view.NodeModel;
import org.phyloviz.pwp.service.dtos.tree_view.SaveTreeViewInput;

import java.util.List;

/**
 * Input model for the save tree view endpoint.
 */
@Data
public class SaveTreeViewInputModel {
    private List<NodeModel> nodes;
    private TransformationsModel transformations;

    public SaveTreeViewInput toDto() {
        return new SaveTreeViewInput(nodes.stream().map(NodeModel::toDto).toList(), transformations.toDto());
    }
}
