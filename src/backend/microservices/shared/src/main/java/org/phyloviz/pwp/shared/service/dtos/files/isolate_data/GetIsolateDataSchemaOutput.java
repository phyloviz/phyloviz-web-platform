package org.phyloviz.pwp.shared.service.dtos.files.isolate_data;

import lombok.Data;

import java.util.List;

/**
 * Output of the schema of the isolate data.
 */
@Data
public class GetIsolateDataSchemaOutput {
    private final List<String> headers;
}
