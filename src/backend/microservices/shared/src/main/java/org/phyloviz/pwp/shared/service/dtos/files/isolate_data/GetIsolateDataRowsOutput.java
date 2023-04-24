package org.phyloviz.pwp.shared.service.dtos.files.isolate_data;

import lombok.Data;

import java.util.List;

/**
 * Output for the rows of the isolate data.
 */
@Data
public class GetIsolateDataRowsOutput {
    private final List<IsolateDataRow> rows;
    private final int totalCount;
}
