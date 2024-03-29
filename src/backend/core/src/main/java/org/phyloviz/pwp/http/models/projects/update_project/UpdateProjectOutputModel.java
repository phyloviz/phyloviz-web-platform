package org.phyloviz.pwp.http.models.projects.update_project;

import lombok.Data;
import org.phyloviz.pwp.service.dtos.project.UpdateProjectOutput;

@Data
public class UpdateProjectOutputModel {
    private String name;
    private String description;

    public UpdateProjectOutputModel(UpdateProjectOutput updateProjectOutput) {
        if (updateProjectOutput.getNewName() != null) {
            this.name = String.format("Changed from '%s' to '%s'", updateProjectOutput.getPreviousName(), updateProjectOutput.getNewName());
        } else {
            this.name = "No changes";
        }
        if (updateProjectOutput.getNewDescription() != null) {
            this.description = String.format("Changed from '%s' to '%s'", updateProjectOutput.getPreviousDescription(), updateProjectOutput.getNewDescription());
        } else {
            this.description = "No changes";
        }
    }
}
