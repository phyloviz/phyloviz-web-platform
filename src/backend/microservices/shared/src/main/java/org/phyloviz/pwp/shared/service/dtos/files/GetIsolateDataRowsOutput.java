package org.phyloviz.pwp.shared.service.dtos.files;

import lombok.Data;

import java.util.List;

/**
 * Output DTO for the getTypingDataProfiles service.
 */
@Data
public class GetIsolateDataRowsOutput {
    private final List<IsolateDataRowDTO> rows;
    private final int totalCount;
}
