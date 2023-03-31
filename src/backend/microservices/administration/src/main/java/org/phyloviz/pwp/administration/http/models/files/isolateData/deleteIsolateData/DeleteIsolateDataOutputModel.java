package org.phyloviz.pwp.administration.http.models.files.isolateData.deleteIsolateData;

import lombok.Data;
import org.phyloviz.pwp.administration.service.dtos.files.deleteIsolateData.DeleteIsolateDataOutputDTO;

@Data
public class DeleteIsolateDataOutputModel {
    private String projectId;
    private String isolateDataId;

    public DeleteIsolateDataOutputModel(DeleteIsolateDataOutputDTO deleteIsolateDataOutputDTO) {
        this.projectId = deleteIsolateDataOutputDTO.getProjectId();
        this.isolateDataId = deleteIsolateDataOutputDTO.getIsolateDataId();
    }
}
