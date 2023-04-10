package org.phyloviz.pwp.visualization.service;

import org.phyloviz.pwp.visualization.service.dtos.getDistanceMatrix.GetDistanceMatrixInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.getTree.GetTreeInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.getTreeView.GetTreeViewInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.getTreeView.GetTreeViewOutputDTO;
import org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataRows.GetIsolateDataRowsInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataRows.GetIsolateDataRowsOutputDTO;
import org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataSchema.GetIsolateDataSchemaInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.isolateData.getIsolateDataSchema.GetIsolateDataSchemaOutputDTO;
import org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataProfiles.GetTypingDataProfilesInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataProfiles.GetTypingDataProfilesOutputDTO;
import org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataSchema.GetTypingDataSchemaInputDTO;
import org.phyloviz.pwp.visualization.service.dtos.typingData.getTypingDataSchema.GetTypingDataSchemaOutputDTO;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link VisualizationService} interface.
 */
@Service
public class VisualizationServiceImpl implements VisualizationService {

    @Override
    public String getTree(GetTreeInputDTO getTreeInputDTO) {
        return null;
    }

    @Override
    public GetTreeViewOutputDTO getTreeView(GetTreeViewInputDTO getTreeViewInputDTO) {
        return null;
    }

    @Override
    public String getDistanceMatrix(GetDistanceMatrixInputDTO getDistanceMatrixInputDTO) {
        return null;
    }

    @Override
    public GetTypingDataSchemaOutputDTO getTypingDataSchema(GetTypingDataSchemaInputDTO getTypingDataSchemaInputDTO) {
        return null;
    }

    @Override
    public GetTypingDataProfilesOutputDTO getTypingDataProfiles(GetTypingDataProfilesInputDTO getTypingDataProfilesInputDTO) {
        return null;
    }

    @Override
    public GetIsolateDataSchemaOutputDTO getIsolateDataSchema(GetIsolateDataSchemaInputDTO getIsolateDataSchemaInputDTO) {
        return null;
    }

    @Override
    public GetIsolateDataRowsOutputDTO getIsolateDataRows(GetIsolateDataRowsInputDTO getIsolateDataRowsInputDTO) {
        return null;
    }
}
