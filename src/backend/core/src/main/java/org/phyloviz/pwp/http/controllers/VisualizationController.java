package org.phyloviz.pwp.http.controllers;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.http.models.get_tree_view.GetTreeViewOutputModel;
import org.phyloviz.pwp.http.models.isolate_data.get_isolate_data_rows.GetIsolateDataRowsOutputModel;
import org.phyloviz.pwp.http.models.isolate_data.get_isolate_data_schema.GetIsolateDataKeysOutputModel;
import org.phyloviz.pwp.http.models.save_tree_view.SaveTreeViewInputModel;
import org.phyloviz.pwp.http.models.save_tree_view.SaveTreeViewOutputModel;
import org.phyloviz.pwp.http.models.typing_data.get_typing_data_profiles.GetTypingDataProfilesOutputModel;
import org.phyloviz.pwp.http.models.typing_data.get_typing_data_schema.GetTypingDataSchemaOutputModel;
import org.phyloviz.pwp.service.VisualizationService;
import org.phyloviz.pwp.domain.User;
import org.phyloviz.pwp.service.dtos.files.isolate_data.GetIsolateDataRowsOutput;
import org.phyloviz.pwp.service.dtos.files.typing_data.GetTypingDataProfilesOutput;
import org.phyloviz.pwp.service.dtos.files.typing_data.GetTypingDataSchemaOutput;
import org.phyloviz.pwp.service.dtos.tree_view.GetTreeViewOutput;
import org.phyloviz.pwp.service.dtos.tree_view.SaveTreeViewOutput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * Saves a tree view coordinates and applied transformations.
     *
     * @param projectId  the id of the project
     * @param datasetId  the id of the dataset
     * @param treeViewId the id of the tree view
     * @param inputModel the input model
     * @param user       the user
     * @return the tree view
     */
    @PostMapping("/projects/{projectId}/datasets/{datasetId}/tree-views/{treeViewId}")
    public SaveTreeViewOutputModel saveTreeView(
            @PathVariable String projectId,
            @PathVariable String datasetId,
            @PathVariable String treeViewId,
            @RequestBody SaveTreeViewInputModel inputModel,
            User user
    ) {
        SaveTreeViewOutput saveTreeViewOutput = visualizationService.saveTreeView(
                projectId, datasetId, treeViewId,
                inputModel.toDto(), user.getId());

        return new SaveTreeViewOutputModel(saveTreeViewOutput);
    }

    /**
     * Gets a typing data's schema.
     *
     * @param projectId    the id of the project
     * @param typingDataId the id of the typing data
     * @param user         the user
     * @return the typing data schema
     */
    @GetMapping("/projects/{projectId}/files/typing-data/{typingDataId}/schema")
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
     * Gets the keys of an isolate data.
     *
     * @param projectId     the id of the project
     * @param isolateDataId the id of the isolate data
     * @param user          the user
     * @return the isolate data keys
     */
    @GetMapping("/projects/{projectId}/files/isolate-data/{isolateDataId}/keys")
    public GetIsolateDataKeysOutputModel getIsolateDataKeys(
            @PathVariable String projectId,
            @PathVariable String isolateDataId,
            User user
    ) {
        List<String> keys = visualizationService.getIsolateDataKeys(
                projectId, isolateDataId, user.getId()
        );

        return new GetIsolateDataKeysOutputModel(keys);
    }

    /**
     * Gets the rows of an isolate data, with pagination.
     *
     * @param projectId     the id of the project
     * @param isolateDataId the id of the isolate data
     * @param limit         the limit of rows to return
     * @param offset        the offset of rows to return
     * @param user          the user
     * @return the isolate data rows
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
