package org.phyloviz.pwp.visualization.http.controllers;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.visualization.http.controllers.models.getTreeView.GetTreeViewOutputModel;
import org.phyloviz.pwp.visualization.http.controllers.models.isolateData.getIsolateDataRows.GetIsolateDataRowsOutputModel;
import org.phyloviz.pwp.visualization.http.controllers.models.isolateData.getIsolateDataSchema.GetIsolateDataSchemaOutputModel;
import org.phyloviz.pwp.visualization.http.controllers.models.typingData.getTypingDataProfiles.GetTypingDataProfilesOutputModel;
import org.phyloviz.pwp.visualization.http.controllers.models.typingData.getTypingDataSchema.GetTypingDataSchemaOutputModel;
import org.phyloviz.pwp.visualization.service.VisualizationService;
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
import org.springframework.web.bind.annotation.*;

/**
 * Controller for the Visualization Microservice.
 */
@RestController
@RequestMapping("/visualization")
@RequiredArgsConstructor
public class VisualizationController {

    private final VisualizationService visualizationService;

    /**
     * Gets a tree.
     *
     * @param projectId the id of the project
     * @param datasetId the id of the dataset
     * @param treeId    the id of the tree
     * @return the tree in newick string format
     */
    @GetMapping("/projects/{projectId}/datasets/{datasetId}/trees/{treeId}")
    public String getTree(
            @PathVariable String projectId,
            @PathVariable String datasetId,
            @PathVariable String treeId
    ) {
        return visualizationService.getTree(new GetTreeInputDTO(projectId, datasetId, treeId));
    }

    /**
     * Gets a tree.
     *
     * @param projectId  the id of the project
     * @param datasetId  the id of the dataset
     * @param treeViewId the id of the tree view
     * @return the tree view output
     */
    @GetMapping("/projects/{projectId}/datasets/{datasetId}/tree-views/{treeViewId}")
    public GetTreeViewOutputModel getTreeView(
            @PathVariable String projectId,
            @PathVariable String datasetId,
            @PathVariable String treeViewId
    ) {
        GetTreeViewOutputDTO getTreeViewOutputDTO = visualizationService.getTreeView(
                new GetTreeViewInputDTO(projectId, datasetId, treeViewId)
        );

        return new GetTreeViewOutputModel(getTreeViewOutputDTO);
    }

    /**
     * Gets a distance matrix.
     *
     * @param projectId        the id of the project
     * @param datasetId        the id of the dataset
     * @param distanceMatrixId the id of the distance matrix
     * @return the distance matrix in string
     */
    @GetMapping("/projects/{projectId}/datasets/{datasetId}/distance-matrices/{distanceMatrixId}")
    public String getDistanceMatrix(
            @PathVariable String projectId,
            @PathVariable String datasetId,
            @PathVariable String distanceMatrixId
    ) {
        return visualizationService.getDistanceMatrix(
                new GetDistanceMatrixInputDTO(projectId, datasetId, distanceMatrixId)
        );
    }

    /**
     * Gets a typing data's schema.
     *
     * @param projectId        the id of the project
     * @param typingDataId     the id of the typing data
     * @return the typing data schema
     */
    @GetMapping("/projects/{projectId}/files/typing-data/{typingDataId}")
    public GetTypingDataSchemaOutputModel getTypingDataSchema(
            @PathVariable String projectId,
            @PathVariable String typingDataId
    ) {
        GetTypingDataSchemaOutputDTO getTypingDataSchemaOutputDTO = visualizationService.getTypingDataSchema(
                new GetTypingDataSchemaInputDTO(projectId, typingDataId)
        );

        return new GetTypingDataSchemaOutputModel(getTypingDataSchemaOutputDTO);
    }

    /**
     * Gets a typing data's profiles, with pagination.
     *
     * @param projectId        the id of the project
     * @param typingDataId     the id of the typing data
     * @param limit            the limit of profiles to return
     * @param offset           the offset of profiles to return
     * @return the typing data's profiles
     */
    @GetMapping("/projects/{projectId}/files/typing-data/{typingDataId}/profiles")
    public GetTypingDataProfilesOutputModel getTypingDataProfiles(
            @PathVariable String projectId,
            @PathVariable String typingDataId,
            @RequestParam int limit,
            @RequestParam int offset
    ) {
        GetTypingDataProfilesOutputDTO getTypingDataProfilesOutputDTO = visualizationService.getTypingDataProfiles(
                new GetTypingDataProfilesInputDTO(projectId, typingDataId, limit, offset)
        );

        return new GetTypingDataProfilesOutputModel(getTypingDataProfilesOutputDTO);
    }

    /**
     * Gets an isolate data's schema.
     *
     * @param projectId        the id of the project
     * @param isolateDataId     the id of the isolate data
     * @return the isolate data schema
     */
    @GetMapping("/projects/{projectId}/files/isolate-data/{isolateDataId}")
    public GetIsolateDataSchemaOutputModel getIsolateDataSchema(
            @PathVariable String projectId,
            @PathVariable String isolateDataId
    ) {
        GetIsolateDataSchemaOutputDTO getIsolateDataSchemaOutputDTO = visualizationService.getIsolateDataSchema(
                new GetIsolateDataSchemaInputDTO(projectId, isolateDataId)
        );

        return new GetIsolateDataSchemaOutputModel(getIsolateDataSchemaOutputDTO);
    }

    /**
     * Gets an isolate data's rows, with pagination.
     *
     * @param projectId        the id of the project
     * @param isolateDataId     the id of the isolate data
     * @param limit            the limit of rows to return
     * @param offset           the offset of rows to return
     * @return the isolate data's rows
     */
    @GetMapping("/projects/{projectId}/files/isolate-data/{isolateDataId}/rows")
    public GetIsolateDataRowsOutputModel getIsolateDataRows(
            @PathVariable String projectId,
            @PathVariable String isolateDataId,
            @RequestParam int limit,
            @RequestParam int offset
    ) {
        GetIsolateDataRowsOutputDTO getIsolateDataRowsOutputDTO = visualizationService.getIsolateDataRows(
                new GetIsolateDataRowsInputDTO(projectId, isolateDataId, limit, offset)
        );

        return new GetIsolateDataRowsOutputModel(getIsolateDataRowsOutputDTO);
    }
}
