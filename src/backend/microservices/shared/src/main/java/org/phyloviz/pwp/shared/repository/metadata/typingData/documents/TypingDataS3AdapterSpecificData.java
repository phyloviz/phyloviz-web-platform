package org.phyloviz.pwp.shared.repository.metadata.typingData.documents;

import lombok.Data;

@Data
public class TypingDataS3AdapterSpecificData implements TypingDataAdapterSpecificData {
    private final String originalFilename;
}
