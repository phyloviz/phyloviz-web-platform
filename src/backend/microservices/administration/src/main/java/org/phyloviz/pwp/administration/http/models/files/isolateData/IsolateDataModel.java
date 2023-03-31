package org.phyloviz.pwp.administration.http.models.files.isolateData;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.files.IsolateDataDTO;

@Data
public class IsolateDataModel {
    private String isolateDataId;
    private String name;

    public IsolateDataModel(IsolateDataDTO isolateDataDTO) {
        this.isolateDataId = isolateDataDTO.getIsolateDataId();
        this.name = isolateDataDTO.getName();
    }
}
