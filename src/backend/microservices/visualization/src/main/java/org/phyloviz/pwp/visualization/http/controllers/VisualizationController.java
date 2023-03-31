package org.phyloviz.pwp.visualization.http.controllers;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.visualization.http.controllers.models.getTypingDataProfiles.GetTypingDataProfilesOutputModel;
import org.phyloviz.pwp.visualization.http.controllers.models.getTreeView.GetTreeViewOutputModel;
import org.phyloviz.pwp.visualization.service.VisualizationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the Visualization Microservice.
 */
@RestController
@RequestMapping("/visualization")
@RequiredArgsConstructor
public class VisualizationController {

    private final VisualizationService visualizationService;

    /**
     * Gets the dataset information, given its id.
     *
     * @param id the id of the dataset
     * @return the dataset information
     */
    @GetMapping("/datasets/{id}")
    public org.phyloviz.pwp.visualization.http.controllers.models.getTypingDataDetails.GetTypingDataDetailsOutputModel getDatasetDetails(@PathVariable String id) {
        return new org.phyloviz.pwp.visualization.http.controllers.models.getTypingDataDetails.GetTypingDataDetailsOutputModel(visualizationService.getDatasetDetails(id));
    }

    /**
     * Gets the profiles of a typing data, given its id.
     *
     * @param id     the id of the typing data
     * @param limit  the number of profiles to be returned
     * @param offset the offset of the profiles to be returned
     * @return the profiles of the typing data
     */
    @GetMapping("/typing-data/{id}/profiles")
    public GetTypingDataProfilesOutputModel getTypingDataProfiles(
            @PathVariable String id,
            @RequestParam("limit") int limit,
            @RequestParam("offset") int offset
    ) {
        return new GetTypingDataProfilesOutputModel(visualizationService.getTypingDataProfiles(id, limit, offset));
    }

    /**
     * Gets the tree view, given its id.
     *
     * @param id the id of the tree view
     * @return the tree view
     */
    @GetMapping("/treeViews/{id}")
    public GetTreeViewOutputModel getTreeView(@PathVariable String id) {
        return new GetTreeViewOutputModel(visualizationService.getTreeView(id));
    }
}
