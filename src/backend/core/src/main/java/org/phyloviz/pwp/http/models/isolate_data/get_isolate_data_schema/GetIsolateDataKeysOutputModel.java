package org.phyloviz.pwp.http.models.isolate_data.get_isolate_data_schema;

import lombok.Data;

import java.util.List;

/**
 * Output model for the GetIsolateDataKeys endpoint.
 */
@Data
public class GetIsolateDataKeysOutputModel {
    private final List<String> keys;
}
