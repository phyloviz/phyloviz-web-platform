package org.phyloviz.pwp.administration.http.models.files.typing_data.update_typing_data;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.files.typing_data.UpdateTypingDataOutput;

@Data
public class UpdateTypingDataOutputModel {
    private String name;

    public UpdateTypingDataOutputModel(UpdateTypingDataOutput updateTypingDataOutput) {
        if (updateTypingDataOutput.getNewName() != null) {
            this.name = String.format("Changed from '%s' to '%s'", updateTypingDataOutput.getPreviousName(), updateTypingDataOutput.getNewName());
        }
    }
}
