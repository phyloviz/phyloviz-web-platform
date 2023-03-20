package org.phyloviz.pwp.visualization.service;

import org.phyloviz.pwp.visualization.service.dtos.getTypingDatasetDetais.GetDatasetDetailsOutputDTO;
import org.phyloviz.pwp.visualization.service.dtos.getTypingDatasetProfiles.GetTypingDatasetProfilesOutputDTO;
import org.phyloviz.pwp.visualization.service.dtos.getTreeView.GetTreeViewOutputDTO;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link VisualizationService} interface.
 */
@Service
public class VisualizationServiceImpl implements VisualizationService {

    @Override
    public GetDatasetDetailsOutputDTO getDatasetDetails(String id) {
        return null;
    }

    @Override
    public GetTypingDatasetProfilesOutputDTO getTypingDatasetProfiles(String id, int limit, int offset) {
        return null;
    }

    @Override
    public GetTreeViewOutputDTO getTreeView(String id) {
        return null;
    }
}
