package org.phyloviz.pwp.administration.service.dtos.files;

import lombok.Data;

import java.util.List;

@Data
public class FilesDTO {
    private final List<TypingDataDTO> typingData;
    private final List<IsolateDataDTO> isolateData;
}
