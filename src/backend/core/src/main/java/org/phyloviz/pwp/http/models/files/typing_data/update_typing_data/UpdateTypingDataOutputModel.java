package org.phyloviz.pwp.http.models.files.typing_data.update_typing_data;

import lombok.Data;
import org.phyloviz.pwp.service.dtos.files.typing_data.UpdateTypingDataOutput;

@Data
public class UpdateTypingDataOutputModel {
    private String name;

    public UpdateTypingDataOutputModel(UpdateTypingDataOutput updateTypingDataOutput) {
        if (updateTypingDataOutput.getNewName() != null) {
            this.name = String.format("Changed from '%s' to '%s'", updateTypingDataOutput.getPreviousName(), updateTypingDataOutput.getNewName());
        } else {
            this.name = "No changes";
        }
    }
}
