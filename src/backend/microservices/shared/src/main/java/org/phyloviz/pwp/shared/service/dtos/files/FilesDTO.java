package org.phyloviz.pwp.shared.service.dtos.files;

import lombok.Data;

import java.util.List;

@Data
public class FilesDTO {
    private final List<TypingDataMetadataDTO> typingData;
    private final List<IsolateDataMetadataDTO> isolateData;
}
