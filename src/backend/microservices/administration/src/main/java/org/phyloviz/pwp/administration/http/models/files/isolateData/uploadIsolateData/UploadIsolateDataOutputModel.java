package org.phyloviz.pwp.administration.http.models.files.isolateData.uploadIsolateData;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.files.uploadIsolateData.UploadIsolateDataOutputDTO;

@Data
public class UploadIsolateDataOutputModel {
    private String projectId;
    private String isolateDataId;

    public UploadIsolateDataOutputModel(UploadIsolateDataOutputDTO uploadIsolateDataOutputDTO) {
        this.projectId = uploadIsolateDataOutputDTO.getProjectId();
        this.isolateDataId = uploadIsolateDataOutputDTO.getIsolateDataId();
    }
}
