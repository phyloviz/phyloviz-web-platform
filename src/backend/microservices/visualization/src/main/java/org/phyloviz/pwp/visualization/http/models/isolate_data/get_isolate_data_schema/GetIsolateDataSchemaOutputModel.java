package org.phyloviz.pwp.visualization.http.models.isolate_data.get_isolate_data_schema;

import lombok.Data;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataSchemaOutput;

import java.util.List;

/**
 * Output model for the get typing data profiles endpoint.
 */
@Data
public class GetIsolateDataSchemaOutputModel {
    private final List<String> headers;

    public GetIsolateDataSchemaOutputModel(GetIsolateDataSchemaOutput getIsolateDataSchemaOutput) {
        this.headers = getIsolateDataSchemaOutput.getHeaders();
    }
}
