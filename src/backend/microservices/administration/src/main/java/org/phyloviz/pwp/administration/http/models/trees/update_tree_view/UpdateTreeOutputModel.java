package org.phyloviz.pwp.administration.http.models.trees.update_tree_view;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.tree.UpdateTreeOutput;

@Data
public class UpdateTreeOutputModel {
    private String name;

    public UpdateTreeOutputModel(UpdateTreeOutput updateTreeOutput) {
        if (updateTreeOutput.getNewName() != null) {
            this.name = String.format("Changed from '%s' to '%s'", updateTreeOutput.getPreviousName(), updateTreeOutput.getNewName());
        } else {
            this.name = "No changes";
        }
    }
}
