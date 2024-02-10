package org.phyloviz.pwp.http.models.datasets.update_dataset;

import lombok.Data;
import org.phyloviz.pwp.service.dtos.dataset.UpdateDatasetOutput;

@Data
public class UpdateDatasetOutputModel {
    private String name;
    private String description;

    public UpdateDatasetOutputModel(UpdateDatasetOutput updateDatasetOutput) {
        if (updateDatasetOutput.getNewName() != null) {
            this.name = String.format("Changed from '%s' to '%s'", updateDatasetOutput.getPreviousName(), updateDatasetOutput.getNewName());
        } else {
            this.name = "No changes";
        }
        if (updateDatasetOutput.getNewDescription() != null) {
            this.description = String.format("Changed from '%s' to '%s'", updateDatasetOutput.getPreviousDescription(), updateDatasetOutput.getNewDescription());
        } else {
            this.description = "No changes";
        }
    }
}
