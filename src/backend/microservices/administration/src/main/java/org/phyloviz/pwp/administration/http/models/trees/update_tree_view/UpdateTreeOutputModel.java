package org.phyloviz.pwp.administration.http.models.trees.update_tree_view;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.tree.UpdateTreeOutput;
import org.phyloviz.pwp.administration.service.dtos.tree_view.UpdateTreeViewOutput;

@Data
public class UpdateTreeOutputModel {
    private String name;

    public UpdateTreeOutputModel(UpdateTreeOutput updateTreeOutput) {
        if(updateTreeOutput.getNewName() != null) {
            this.name = String.format("Changed from '%s' to '%s'", updateTreeOutput.getPreviousName(), updateTreeOutput.getNewName());
        }
    }
}
