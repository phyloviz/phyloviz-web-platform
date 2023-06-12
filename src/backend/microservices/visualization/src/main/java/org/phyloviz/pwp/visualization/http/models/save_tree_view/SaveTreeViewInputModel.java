package org.phyloviz.pwp.visualization.http.models.save_tree_view;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.tree_view.SaveTreeViewInput;
import org.phyloviz.pwp.visualization.http.models.get_tree_view.NodeModel;

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
