package org.phyloviz.pwp.administration.http.models.files.isolateData;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.files.IsolateDataMetadataDTO;

@Data
public class IsolateDataModel {
    private String isolateDataId;
    private String name;

    public IsolateDataModel(IsolateDataMetadataDTO isolateDataMetadataDTO) {
        this.isolateDataId = isolateDataMetadataDTO.getIsolateDataId();
        this.name = isolateDataMetadataDTO.getName();
    }
}
