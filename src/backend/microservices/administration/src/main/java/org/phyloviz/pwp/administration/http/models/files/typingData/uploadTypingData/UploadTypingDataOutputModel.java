package org.phyloviz.pwp.administration.http.models.files.typingData.uploadTypingData;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.files.uploadTypingData.UploadTypingDataOutputDTO;

@Data
public class UploadTypingDataOutputModel {
    private String projectId;
    private String typingDataId;

    public UploadTypingDataOutputModel(UploadTypingDataOutputDTO uploadTypingDataOutputDTO) {
        this.projectId = uploadTypingDataOutputDTO.getProjectId();
        this.typingDataId = uploadTypingDataOutputDTO.getTypingDataId();
    }
}
