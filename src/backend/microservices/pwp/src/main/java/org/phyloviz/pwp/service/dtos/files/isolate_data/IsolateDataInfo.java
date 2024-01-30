package org.phyloviz.pwp.service.dtos.files.isolate_data;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.isolate_data.documents.IsolateDataMetadata;

import java.util.List;

@Data
public class IsolateDataInfo {
    private final String isolateDataId;
    private final String name;
    private final List<String> keys;

    public IsolateDataInfo(IsolateDataMetadata isolateDataMetadata) {
        this.isolateDataId = isolateDataMetadata.getIsolateDataId();
        this.name = isolateDataMetadata.getName();
        this.keys = isolateDataMetadata.getKeys();
    }
}
