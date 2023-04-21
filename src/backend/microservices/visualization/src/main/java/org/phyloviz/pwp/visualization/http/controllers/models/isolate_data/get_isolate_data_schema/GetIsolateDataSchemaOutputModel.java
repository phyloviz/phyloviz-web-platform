package org.phyloviz.pwp.visualization.http.controllers.models.isolate_data.get_isolate_data_schema;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataSchemaOutput;

/**
 * Output model for the get typing data profiles endpoint.
 */
@Data
public class GetIsolateDataSchemaOutputModel {
    private final String type;
    private final String[] headers;
    private final int totalCount;

    public GetIsolateDataSchemaOutputModel(GetIsolateDataSchemaOutput getIsolateDataSchemaOutput) {
        this.type = getIsolateDataSchemaOutput.getType();
        this.headers = getIsolateDataSchemaOutput.getHeaders();
        this.totalCount = getIsolateDataSchemaOutput.getTotalCount();
    }
}
