package org.phyloviz.pwp.http.models.isolate_data;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.UploadIsolateDataOutput;

@Data
public class UploadIsolateDataOutputModel {
    private String projectId;
    private String isolateDataId;

    public UploadIsolateDataOutputModel(UploadIsolateDataOutput uploadIsolateDataOutput) {
        this.projectId = uploadIsolateDataOutput.getProjectId();
        this.isolateDataId = uploadIsolateDataOutput.getIsolateDataId();
    }
}
