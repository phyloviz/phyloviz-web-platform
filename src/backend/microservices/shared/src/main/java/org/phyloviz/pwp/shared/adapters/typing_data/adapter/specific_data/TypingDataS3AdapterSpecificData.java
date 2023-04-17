package org.phyloviz.pwp.shared.adapters.typing_data.adapter.specific_data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypingDataS3AdapterSpecificData implements TypingDataAdapterSpecificData {
    private String url;
    private String originalFilename;
}
