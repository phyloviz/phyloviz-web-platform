package org.phyloviz.pwp.shared.service.dtos.files;

import lombok.Data;
import org.phyloviz.pwp.shared.repository.metadata.isolateData.documents.IsolateDataMetadata;

@Data
public class IsolateDataMetadataDTO {
    private final String isolateDataId;
    private final String name;

    public IsolateDataMetadataDTO(IsolateDataMetadata isolateDataMetadata) {
        this.isolateDataId = isolateDataMetadata.getIsolateDataId();
        this.name = isolateDataMetadata.getName();
    }
}
