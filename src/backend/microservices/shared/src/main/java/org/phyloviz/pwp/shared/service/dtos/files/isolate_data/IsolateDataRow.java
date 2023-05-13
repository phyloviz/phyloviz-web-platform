package org.phyloviz.pwp.shared.service.dtos.files.isolate_data;

import lombok.Data;

import java.util.Map;

/**
 * Row for the isolate data.
 */
@Data
public class IsolateDataRow {
    private final String id;
    private final Map<String, String> row;
}
