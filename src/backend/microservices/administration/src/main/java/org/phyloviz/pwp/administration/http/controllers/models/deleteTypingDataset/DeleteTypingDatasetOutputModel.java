package org.phyloviz.pwp.administration.http.controllers.models.deleteTypingDataset;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.deleteTypingDataset.DeleteTypingDatasetOutputDTO;

@Data
public class DeleteTypingDatasetOutputModel {
    private String projectId;
    private String typingDatasetId;

    public DeleteTypingDatasetOutputModel(DeleteTypingDatasetOutputDTO deleteTypingDatasetOutputDTO) {
        this.projectId = deleteTypingDatasetOutputDTO.getProjectId();
        this.typingDatasetId = deleteTypingDatasetOutputDTO.getTypingDatasetId();
    }
}
