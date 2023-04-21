package org.phyloviz.pwp.shared.service.dtos.files.typing_data;

import lombok.Data;

@Data
public class UploadTypingDataOutput {
    private final String projectId;
    private final String typingDataId;
}
