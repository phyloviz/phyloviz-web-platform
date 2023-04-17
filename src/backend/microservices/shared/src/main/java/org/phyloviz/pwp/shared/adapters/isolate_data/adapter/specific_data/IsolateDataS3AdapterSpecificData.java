package org.phyloviz.pwp.shared.adapters.isolate_data.adapter.specific_data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IsolateDataS3AdapterSpecificData implements IsolateDataAdapterSpecificData {
    private String url;
    private String originalFilename;
}
