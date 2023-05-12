package org.phyloviz.pwp.shared.service.dtos.files.isolate_data;

import lombok.Data;

import java.util.List;

/**
 * Row for the isolate data.
 */
@Data
public class IsolateDataRow {
    private final String id;
    private final List<String> row;
}
