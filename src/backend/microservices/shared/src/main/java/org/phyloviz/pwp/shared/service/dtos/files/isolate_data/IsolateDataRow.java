package org.phyloviz.pwp.shared.service.dtos.files.isolate_data;

import lombok.Data;

/**
 * DTO for the isolate data.
 */
@Data
public class IsolateDataRow {
    private final int id;
    private final String[] row;
}
