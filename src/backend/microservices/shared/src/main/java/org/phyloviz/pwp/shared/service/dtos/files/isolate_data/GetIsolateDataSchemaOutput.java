package org.phyloviz.pwp.shared.service.dtos.files.isolate_data;

import lombok.Data;

/**
 * Output DTO for the getTypingDataDetails service.
 */
@Data
public class GetIsolateDataSchemaOutput {
    private final String type;
    private final String[] headers;
    private final int totalCount;
}