package org.phyloviz.pwp.administration.http.models.files.typingData.uploadTypingData;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.files.UploadTypingDataOutput;

@Data
public class UploadTypingDataOutputModel {
    private String projectId;
    private String typingDataId;

    public UploadTypingDataOutputModel(UploadTypingDataOutput uploadTypingDataOutput) {
        this.projectId = uploadTypingDataOutput.getProjectId();
        this.typingDataId = uploadTypingDataOutput.getTypingDataId();
    }
}
