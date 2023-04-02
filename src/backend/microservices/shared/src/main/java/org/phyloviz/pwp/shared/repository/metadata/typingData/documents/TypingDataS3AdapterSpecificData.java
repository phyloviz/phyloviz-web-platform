package org.phyloviz.pwp.shared.repository.metadata.typingData.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeAlias("typingDataS3AdapterSpecificData")
public class TypingDataS3AdapterSpecificData implements TypingDataAdapterSpecificData {
    private String originalFilename;
}
