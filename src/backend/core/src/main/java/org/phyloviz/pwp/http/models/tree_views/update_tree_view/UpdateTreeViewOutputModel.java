package org.phyloviz.pwp.http.models.tree_views.update_tree_view;

import lombok.Data;
import org.phyloviz.pwp.service.dtos.tree_view.UpdateTreeViewOutput;

@Data
public class UpdateTreeViewOutputModel {
    private String name;

    public UpdateTreeViewOutputModel(UpdateTreeViewOutput updateTreeViewOutput) {
        if (updateTreeViewOutput.getNewName() != null) {
            this.name = String.format("Changed from '%s' to '%s'", updateTreeViewOutput.getPreviousName(), updateTreeViewOutput.getNewName());
        } else {
            this.name = "No changes";
        }
    }
}
