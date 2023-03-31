package org.phyloviz.pwp.shared.repository.metadata.isolateData.documents;

import lombok.Data;

@Data
public class IsolateDataS3AdapterSpecificData implements IsolateDataAdapterSpecificData{
    private final String originalFilename;
}
