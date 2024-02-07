package org.phyloviz.pwp.repository.data.typing_data.repository.specific_data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypingDataS3DataRepositorySpecificData implements TypingDataDataRepositorySpecificData {
    private String url;
    private String originalFilename;
}
