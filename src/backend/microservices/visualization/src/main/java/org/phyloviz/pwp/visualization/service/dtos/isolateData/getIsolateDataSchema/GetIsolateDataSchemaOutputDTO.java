package org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataSchema;

import lombok.Data;

/**
 * Output DTO for the getTypingDataDetails service.
 */
@Data
public class GetIsolateDataSchemaOutputDTO {
    private final String type;
    private final String[] headers;
    private final int totalCount;
}
