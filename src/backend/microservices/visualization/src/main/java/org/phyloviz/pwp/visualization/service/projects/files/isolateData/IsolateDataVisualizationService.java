package org.phyloviz.pwp.visualization.service.projects.files.isolateData;

import org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataRows.GetIsolateDataRowsInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataRows.GetIsolateDataRowsOutputDTO;
import org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataSchema.GetIsolateDataSchemaInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataSchema.GetIsolateDataSchemaOutputDTO;

public interface IsolateDataVisualizationService {

    /**
     * Gets an isolate data's schema, given its id.
     *
     * @param getIsolateDataSchemaInputDTO the input DTO
     * @return the isolate data schema
     */
    GetIsolateDataSchemaOutputDTO getIsolateDataSchema(GetIsolateDataSchemaInputDTO getIsolateDataSchemaInputDTO);

    /**
     * Gets an isolate data's rows, given its id, with pagination.
     *
     * @param getIsolateDataRowsInputDTO the input DTO
     * @return the isolate data rows
     */
    GetIsolateDataRowsOutputDTO getIsolateDataRows(GetIsolateDataRowsInputDTO getIsolateDataRowsInputDTO);
}
