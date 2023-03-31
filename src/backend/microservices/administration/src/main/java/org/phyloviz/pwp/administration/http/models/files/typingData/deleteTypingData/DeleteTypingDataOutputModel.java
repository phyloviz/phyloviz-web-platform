package org.phyloviz.pwp.administration.http.models.files.typingData.deleteTypingData;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.files.deleteTypingData.DeleteTypingDataOutputDTO;

@Data
public class DeleteTypingDataOutputModel {
    private String projectId;
    private String typingDataId;

    public DeleteTypingDataOutputModel(DeleteTypingDataOutputDTO deleteTypingDataOutputDTO) {
        this.projectId = deleteTypingDataOutputDTO.getProjectId();
        this.typingDataId = deleteTypingDataOutputDTO.getTypingDataId();
    }
}
