package org.phyloviz.pwp.visualization.service.projects.files.isolateData;

import org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataRows.GetIsolateDataRowsInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataRows.GetIsolateDataRowsOutputDTO;
import org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataSchema.GetIsolateDataSchemaInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataSchema.GetIsolateDataSchemaOutputDTO;
import org.springframework.stereotype.Service;

@Service
public class IsolateDataVisualizationServiceImpl implements IsolateDataVisualizationService {
    @Override
    public GetIsolateDataSchemaOutputDTO getIsolateDataSchema(GetIsolateDataSchemaInputDTO getIsolateDataSchemaInputDTO) {
        return null;
    }

    @Override
    public GetIsolateDataRowsOutputDTO getIsolateDataRows(GetIsolateDataRowsInputDTO getIsolateDataRowsInputDTO) {
        return null;
    }
}
