package org.phyloviz.pwp.administration.service.dtos.files.isolate_data;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;

@Data
public class IsolateDataInfo {
    private final String isolateDataId;
    private final String name;

    public IsolateDataInfo(IsolateDataMetadata isolateDataMetadata) {
        this.isolateDataId = isolateDataMetadata.getIsolateDataId();
        this.name = isolateDataMetadata.getName();
    }
}
