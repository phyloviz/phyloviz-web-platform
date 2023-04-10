package org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataRows;

import lombok.Data;
import org.phyloviz.pwp.visualization.service.dtos.isolateData.IsolateDataRowDTO;

import java.util.List;

/**
 * Output DTO for the getTypingDataProfiles service.
 */
@Data
public class GetIsolateDataRowsOutputDTO {
    private final List<IsolateDataRowDTO> rows;
    private final int totalCount;
}
