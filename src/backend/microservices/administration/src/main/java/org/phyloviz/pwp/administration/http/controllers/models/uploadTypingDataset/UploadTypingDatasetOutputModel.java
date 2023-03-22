package org.phyloviz.pwp.administration.http.controllers.models.uploadTypingDataset;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.uploadTypingDataset.UploadTypingDatasetOutputDTO;

@Data
public class UploadTypingDatasetOutputModel {
    private final String typingDatasetId;

    public UploadTypingDatasetOutputModel(UploadTypingDatasetOutputDTO uploadTypingDatasetOutputDTO) {
        this.typingDatasetId = uploadTypingDatasetOutputDTO.getTypingDatasetId();
    }
}
