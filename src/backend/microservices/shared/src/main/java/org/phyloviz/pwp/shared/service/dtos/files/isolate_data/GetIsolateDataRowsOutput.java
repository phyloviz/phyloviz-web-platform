package org.phyloviz.pwp.shared.service.dtos.files.isolate_data;

import lombok.Data;

import java.util.List;

/**
 * Output DTO for the getTypingDataProfiles service.
 */
@Data
public class GetIsolateDataRowsOutput {
    private final List<IsolateDataRow> rows;
    private final int totalCount;
}
