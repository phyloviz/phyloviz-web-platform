package org.phyloviz.pwp.administration.http.models.files.isolate_data;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.files.isolate_data.IsolateDataInfo;

import java.util.List;

@Data
public class IsolateDataModel {
    private String isolateDataId;
    private String name;
    private List<String> keys;

    public IsolateDataModel(IsolateDataInfo isolateDataInfo) {
        this.isolateDataId = isolateDataInfo.getIsolateDataId();
        this.name = isolateDataInfo.getName();
        this.keys = isolateDataInfo.getKeys();
    }
}
