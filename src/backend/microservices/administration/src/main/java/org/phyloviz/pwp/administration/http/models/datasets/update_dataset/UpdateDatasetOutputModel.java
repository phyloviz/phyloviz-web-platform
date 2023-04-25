package org.phyloviz.pwp.administration.http.models.datasets.update_dataset;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.dataset.UpdateDatasetOutput;
import org.phyloviz.pwp.administration.service.dtos.project.UpdateProjectOutput;

@Data
public class UpdateDatasetOutputModel {
    private String name;
    private String description;

    public UpdateDatasetOutputModel(UpdateDatasetOutput updateDatasetOutput) {
        if(updateDatasetOutput.getNewName() != null) {
            this.name = String.format("Changed from '%s' to '%s'", updateDatasetOutput.getPreviousName(), updateDatasetOutput.getNewName());
        }
        if(updateDatasetOutput.getNewDescription() != null) {
            this.description = String.format("Changed from '%s' to '%s'", updateDatasetOutput.getPreviousDescription(), updateDatasetOutput.getNewDescription());
        }
    }
}
