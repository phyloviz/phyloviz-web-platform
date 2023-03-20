package org.phyloviz.pwp.uploader.service.dtos.uploadTypingDataset;

public class UploadTypingDatasetOutputDTO {
    private final String typingDatasetId;

    public UploadTypingDatasetOutputDTO(String typingDatasetId) {
        this.typingDatasetId = typingDatasetId;
    }

    public String getTypingDatasetId() {
        return typingDatasetId;
    }
}
