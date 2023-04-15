package org.phyloviz.pwp.administration.http.models.files.isolateData.deleteIsolateData;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteIsolateDataOutputModel {
    private String projectId;
    private String isolateDataId;
}
