package org.phyloviz.pwp.http.models.typing_data;

import lombok.Data;
import org.phyloviz.pwp.service.dtos.files.typing_data.UploadTypingDataOutput;

@Data
public class UploadTypingDataOutputModel {
    private String projectId;
    private String typingDataId;

    public UploadTypingDataOutputModel(UploadTypingDataOutput uploadTypingDataOutput) {
        this.projectId = uploadTypingDataOutput.getProjectId();
        this.typingDataId = uploadTypingDataOutput.getTypingDataId();
    }
}
