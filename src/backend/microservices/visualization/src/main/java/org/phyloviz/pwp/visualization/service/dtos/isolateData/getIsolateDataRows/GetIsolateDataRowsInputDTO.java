package org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataRows;

import lombok.Data;

@Data
public class GetIsolateDataRowsInputDTO {
    private final String projectId;
    private final String isolateDataId;
    private final int limit;
    private final int offset;
}
