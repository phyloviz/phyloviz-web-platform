package org.phyloviz.pwp.service.dtos.files.typing_data;

import lombok.Data;

@Data
public class UploadTypingDataOutput {
    private final String projectId;
    private final String typingDataId;
}
