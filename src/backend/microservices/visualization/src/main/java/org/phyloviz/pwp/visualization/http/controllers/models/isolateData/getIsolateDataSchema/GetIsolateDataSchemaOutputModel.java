package org.phyloviz.pwp.visualization.http.controllers.models.isolateData.getIsolateDataSchema;

import lombok.Data;
import org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataSchema.GetIsolateDataSchemaOutputDTO;

/**
 * Output model for the get typing data profiles endpoint.
 */
@Data
public class GetIsolateDataSchemaOutputModel {
    private final String type;
    private final String[] headers;
    private final int totalCount;

    public GetIsolateDataSchemaOutputModel(GetIsolateDataSchemaOutputDTO getIsolateDataSchemaOutputDTO) {
        this.type = getIsolateDataSchemaOutputDTO.getType();
        this.headers = getIsolateDataSchemaOutputDTO.getHeaders();
        this.totalCount = getIsolateDataSchemaOutputDTO.getTotalCount();
    }
}
