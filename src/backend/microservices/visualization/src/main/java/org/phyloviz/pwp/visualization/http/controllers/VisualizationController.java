package org.phyloviz.pwp.visualization.http.controllers;

import lombok.AllArgsConstructor;
import org.phyloviz.pwp.visualization.http.controllers.models.getTypingDatasetDetais.GetDatasetDetailsOutputModel;
import org.phyloviz.pwp.visualization.http.controllers.models.getTypingDatasetProfiles.GetTypingDatasetProfilesOutputModel;
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
@AllArgsConstructor
public class VisualizationController {

    private final VisualizationService visualizationService;

    /**
     * Gets the dataset information, given its id.
     *
     * @param id the id of the dataset
     * @return the dataset information
     */
    @GetMapping("/datasets/{id}")
    public GetDatasetDetailsOutputModel getDatasetDetails(@PathVariable String id) {
        return new GetDatasetDetailsOutputModel(visualizationService.getDatasetDetails(id));
    }

    /**
     * Gets the profiles of a typing dataset, given its id.
     *
     * @param id     the id of the typing dataset
     * @param limit  the number of profiles to be returned
     * @param offset the offset of the profiles to be returned
     * @return the profiles of the typing dataset
     */
    @GetMapping("/typing-datasets/{id}/profiles")
    public GetTypingDatasetProfilesOutputModel getTypingDatasetProfiles(
            @PathVariable String id,
            @RequestParam("limit") int limit,
            @RequestParam("offset") int offset
    ) {
        return new GetTypingDatasetProfilesOutputModel(visualizationService.getTypingDatasetProfiles(id, limit, offset));
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
