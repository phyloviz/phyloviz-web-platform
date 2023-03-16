package org.phyloviz.pwp.visualization.service;

import org.phyloviz.pwp.visualization.service.dtos.getDatasetDetais.GetDatasetDetailsOutputDTO;
import org.phyloviz.pwp.visualization.service.dtos.getDatasetProfiles.GetDatasetProfilesOutputDTO;
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
    public GetDatasetProfilesOutputDTO getDatasetProfiles(String id, int limit, int offset) {
        return null;
    }

    @Override
    public GetTreeViewOutputDTO getTreeView(String id) {
        return null;
    }
}
