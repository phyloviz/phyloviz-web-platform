package org.phyloviz.pwp.uploader.http.controllers.models.uploadTypingDataset;

import lombok.Data;
import org.phyloviz.pwp.uploader.service.dtos.uploadTypingDataset.UploadTypingDatasetOutputDTO;

@Data
public class UploadTypingDatasetOutputModel {
    private final String typingDatasetId;

    public UploadTypingDatasetOutputModel(UploadTypingDatasetOutputDTO uploadTypingDatasetOutputDTO) {
        this.typingDatasetId = uploadTypingDatasetOutputDTO.getTypingDatasetId();
    }
}
