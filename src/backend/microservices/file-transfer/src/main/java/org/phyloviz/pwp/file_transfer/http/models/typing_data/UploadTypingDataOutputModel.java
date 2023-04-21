package org.phyloviz.pwp.file_transfer.http.models.typing_data;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.UploadTypingDataOutput;

@Data
public class UploadTypingDataOutputModel {
    private String projectId;
    private String typingDataId;

    public UploadTypingDataOutputModel(UploadTypingDataOutput uploadTypingDataOutput) {
        this.projectId = uploadTypingDataOutput.getProjectId();
        this.typingDataId = uploadTypingDataOutput.getTypingDataId();
    }
}
