package org.phyloviz.pwp.http.models.files.isolate_data.delete_isolate_data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteIsolateDataOutputModel {
    private String projectId;
    private String isolateDataId;
}
