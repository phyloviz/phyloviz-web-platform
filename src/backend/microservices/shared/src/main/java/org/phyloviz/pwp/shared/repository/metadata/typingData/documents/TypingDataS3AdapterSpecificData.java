package org.phyloviz.pwp.shared.repository.metadata.typingData.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypingDataS3AdapterSpecificData implements TypingDataAdapterSpecificData {
    private String originalFilename;
}
