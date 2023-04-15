package org.phyloviz.pwp.administration.http.models.files.isolateData.uploadIsolateData;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.files.UploadIsolateDataOutput;

@Data
public class UploadIsolateDataOutputModel {
    private String projectId;
    private String isolateDataId;

    public UploadIsolateDataOutputModel(UploadIsolateDataOutput uploadIsolateDataOutput) {
        this.projectId = uploadIsolateDataOutput.getProjectId();
        this.isolateDataId = uploadIsolateDataOutput.getIsolateDataId();
    }
}
