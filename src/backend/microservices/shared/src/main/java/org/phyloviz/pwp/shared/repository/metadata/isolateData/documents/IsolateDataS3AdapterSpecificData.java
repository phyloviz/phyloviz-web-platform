package org.phyloviz.pwp.shared.repository.metadata.isolateData.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IsolateDataS3AdapterSpecificData implements IsolateDataAdapterSpecificData{
    private String originalFilename;
}
