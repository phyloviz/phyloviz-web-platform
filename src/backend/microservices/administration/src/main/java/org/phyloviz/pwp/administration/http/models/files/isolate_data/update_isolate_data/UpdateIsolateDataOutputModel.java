package org.phyloviz.pwp.administration.http.models.files.isolate_data.update_isolate_data;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.files.isolate_data.UpdateIsolateDataOutput;

@Data
public class UpdateIsolateDataOutputModel {
    private String name;

    public UpdateIsolateDataOutputModel(UpdateIsolateDataOutput updateIsolateDataOutput) {
        if (updateIsolateDataOutput.getNewName() != null) {
            this.name = String.format("Changed from '%s' to '%s'", updateIsolateDataOutput.getPreviousName(), updateIsolateDataOutput.getNewName());
        }
    }
}
