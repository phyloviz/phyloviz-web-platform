package org.phyloviz.pwp.visualization.service;

import org.phyloviz.pwp.visualization.service.dtos.getTreeView.GetTreeViewOutputDTO;
import org.phyloviz.pwp.visualization.service.dtos.getTypingDataDetails.GetTypingDataDetailsOutputDTO;
import org.phyloviz.pwp.visualization.service.dtos.getTypingDataProfiles.GetTypingDataProfilesOutputDTO;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link VisualizationService} interface.
 */
@Service
public class VisualizationServiceImpl implements VisualizationService {

    @Override
    public GetTypingDataDetailsOutputDTO getDatasetDetails(String id) {
        return null;
    }

    @Override
    public GetTypingDataProfilesOutputDTO getTypingDataProfiles(String id, int limit, int offset) {
        return null;
    }

    @Override
    public GetTreeViewOutputDTO getTreeView(String id) {
        return null;
    }
}
