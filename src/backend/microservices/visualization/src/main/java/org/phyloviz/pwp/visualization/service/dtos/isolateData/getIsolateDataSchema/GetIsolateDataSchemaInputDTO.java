package org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataSchema;

import lombok.Data;

@Data
public class GetIsolateDataSchemaInputDTO {
    private final String projectId;
    private final String isolateDataId;
}
