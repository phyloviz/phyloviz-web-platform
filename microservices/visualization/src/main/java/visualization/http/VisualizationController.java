package visualization.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import visualization.service.VisualizationService;

/**
 * Controller for the Visualization Microservice.
 */
@RestController
@RequestMapping("/visualization")
public class VisualizationController {

    private final VisualizationService visualizationService;

    public VisualizationController(VisualizationService visualizationService) {
        this.visualizationService = visualizationService;
    }

    /**
     * Gets the dataset information, given its id.
     *
     * @param id the id of the dataset
     * @return the dataset information
     */
    @GetMapping("/datasets/{id}")
    public void getDataset(@PathVariable String id) {
        visualizationService.getDataset(id);
    }

    /**
     * Gets the profiles of a dataset, given its id.
     *
     * @param id     the id of the dataset
     * @param limit  the number of profiles to be returned
     * @param offset the offset of the profiles to be returned
     * @return the profiles of the dataset
     */
    @GetMapping("/datasets/{id}/profiles")
    public void getDatasetProfiles(
            @PathVariable String id,
            @RequestParam("limit") int limit,
            @RequestParam("offset") int offset
    ) {
        visualizationService.getDatasetProfiles(id, limit, offset);
    }

    /**
     * Gets the tree view, given its id.
     *
     * @param id the id of the tree view
     * @return the tree view
     */
    @GetMapping("/treeViews/{id}")
    public void getTreeView(@PathVariable String id) {
        visualizationService.getTreeView(id);
    }
}
