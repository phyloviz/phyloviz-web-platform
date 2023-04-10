package org.phyloviz.pwp.visualization.service.dtos.isolateData;

import lombok.Data;

/**
 * DTO for the isolate data.
 */
@Data
public class IsolateDataRowDTO {
    private final int id;
    private final String[] row;
}
