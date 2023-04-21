package org.phyloviz.pwp.visualization.http.controllers;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.domain.User;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataRowsOutput;
import org.phyloviz.pwp.shared.service.dtos.files.isolate_data.GetIsolateDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataProfilesOutput;
import org.phyloviz.pwp.shared.service.dtos.files.typing_data.GetTypingDataSchemaOutput;
import org.phyloviz.pwp.shared.service.dtos.tree_view.GetTreeViewOutput;
import org.phyloviz.pwp.visualization.http.controllers.models.get_tree_view.GetTreeViewOutputModel;
import org.phyloviz.pwp.visualization.http.controllers.models.isolate_data.get_isolate_data_rows.GetIsolateDataRowsOutputModel;
import org.phyloviz.pwp.visualization.http.controllers.models.isolate_data.get_isolate_data_schema.GetIsolateDataSchemaOutputModel;
import org.phyloviz.pwp.visualization.http.controllers.models.typing_data.get_typing_data_profiles.GetTypingDataProfilesOutputModel;
import org.phyloviz.pwp.visualization.http.controllers.models.typing_data.get_typing_data_schema.GetTypingDataSchemaOutputModel;
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
@RequiredArgsConstructor
public class VisualizationController {

    private final VisualizationService visualizationService;

    /**
     * Gets a distance matrix.
     *
     * @param projectId        the id of the project
     * @param datasetId        the id of the dataset
     * @param distanceMatrixId the id of the distance matrix
     * @param user             the user
     * @return the distance matrix in string
     */
    @GetMapping("/projects/{projectId}/datasets/{datasetId}/distance-matrices/{distanceMatrixId}")
    public String getDistanceMatrix(
            @PathVariable String projectId,
            @PathVariable String datasetId,
            @PathVariable String distanceMatrixId,
            User user
    ) {
        return visualizationService.getDistanceMatrix(projectId, datasetId, distanceMatrixId, user.getId());
    }

    /**
     * Gets a tree.
     *
     * @param projectId the id of the project
     * @param datasetId the id of the dataset
     * @param treeId    the id of the tree
     * @param user      the user
     * @return the tree in newick string format
     */
    @GetMapping("/projects/{projectId}/datasets/{datasetId}/trees/{treeId}")
    public String getTree(
            @PathVariable String projectId,
            @PathVariable String datasetId,
            @PathVariable String treeId,
            User user
    ) {
        return visualizationService.getTree(projectId, datasetId, treeId, user.getId());
    }

    /**
     * Gets a tree view.
     *
     * @param projectId  the id of the project
     * @param datasetId  the id of the dataset
     * @param treeViewId the id of the tree view
     * @param user       the user
     * @return the tree view
     */
    @GetMapping("/projects/{projectId}/datasets/{datasetId}/tree-views/{treeViewId}")
    public GetTreeViewOutputModel getTreeView(
            @PathVariable String projectId,
            @PathVariable String datasetId,
            @PathVariable String treeViewId,
            User user
    ) {
        GetTreeViewOutput getTreeViewOutput = visualizationService.getTreeView(projectId, datasetId, treeViewId, user.getId());

        return new GetTreeViewOutputModel(getTreeViewOutput);
    }

    /**
     * Gets a typing data's schema.
     *
     * @param projectId    the id of the project
     * @param typingDataId the id of the typing data
     * @param user         the user
     * @return the typing data schema
     */
    @GetMapping("/projects/{projectId}/files/typing-data/{typingDataId}")
    public GetTypingDataSchemaOutputModel getTypingDataSchema(
            @PathVariable String projectId,
            @PathVariable String typingDataId,
            User user
    ) {
        GetTypingDataSchemaOutput getTypingDataSchemaOutput = visualizationService.getTypingDataSchema(
                projectId, typingDataId, user.getId()
        );

        return new GetTypingDataSchemaOutputModel(getTypingDataSchemaOutput);
    }

    /**
     * Gets a typing data's profiles, with pagination.
     *
     * @param projectId    the id of the project
     * @param typingDataId the id of the typing data
     * @param limit        the limit of profiles to return
     * @param offset       the offset of profiles to return
     * @param user         the user
     * @return the typing data's profiles
     */
    @GetMapping("/projects/{projectId}/files/typing-data/{typingDataId}/profiles")
    public GetTypingDataProfilesOutputModel getTypingDataProfiles(
            @PathVariable String projectId,
            @PathVariable String typingDataId,
            @RequestParam int limit,
            @RequestParam int offset,
            User user
    ) {
        GetTypingDataProfilesOutput getTypingDataProfilesOutput = visualizationService.getTypingDataProfiles(
                projectId, typingDataId, limit, offset, user.getId()
        );

        return new GetTypingDataProfilesOutputModel(getTypingDataProfilesOutput);
    }

    /**
     * Gets an isolate data's schema.
     *
     * @param projectId     the id of the project
     * @param isolateDataId the id of the isolate data
     * @param user          the user
     * @return the isolate data schema
     */
    @GetMapping("/projects/{projectId}/files/isolate-data/{isolateDataId}")
    public GetIsolateDataSchemaOutputModel getIsolateDataSchema(
            @PathVariable String projectId,
            @PathVariable String isolateDataId,
            User user
    ) {
        GetIsolateDataSchemaOutput getIsolateDataSchemaOutput = visualizationService.getIsolateDataSchema(
                projectId, isolateDataId, user.getId()
        );

        return new GetIsolateDataSchemaOutputModel(getIsolateDataSchemaOutput);
    }

    /**
     * Gets an isolate data's rows, with pagination.
     *
     * @param projectId     the id of the project
     * @param isolateDataId the id of the isolate data
     * @param limit         the limit of rows to return
     * @param offset        the offset of rows to return
     * @param user          the user
     * @return the isolate data's rows
     */
    @GetMapping("/projects/{projectId}/files/isolate-data/{isolateDataId}/rows")
    public GetIsolateDataRowsOutputModel getIsolateDataRows(
            @PathVariable String projectId,
            @PathVariable String isolateDataId,
            @RequestParam int limit,
            @RequestParam int offset,
            User user
    ) {
        GetIsolateDataRowsOutput getIsolateDataRowsOutput = visualizationService.getIsolateDataRows(
                projectId, isolateDataId, limit, offset, user.getId()
        );

        return new GetIsolateDataRowsOutputModel(getIsolateDataRowsOutput);
    }
}
