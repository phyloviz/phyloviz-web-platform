package org.phyloviz.pwp.visualization.http.controllers;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.shared.domain.User;
import org.phyloviz.pwp.visualization.http.controllers.models.getTreeView.GetTreeViewOutputModel;
import org.phyloviz.pwp.visualization.http.controllers.models.isolateData.getIsolateDataRows.GetIsolateDataRowsOutputModel;
import org.phyloviz.pwp.visualization.http.controllers.models.isolateData.getIsolateDataSchema.GetIsolateDataSchemaOutputModel;
import org.phyloviz.pwp.visualization.http.controllers.models.typingData.getTypingDataProfiles.GetTypingDataProfilesOutputModel;
import org.phyloviz.pwp.visualization.http.controllers.models.typingData.getTypingDataSchema.GetTypingDataSchemaOutputModel;
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
import org.phyloviz.pwp.visualization.service.projects.datasets.distanceMatrices.DistanceMatrixVisualizationService;
import org.phyloviz.pwp.visualization.service.projects.datasets.treeViews.TreeViewVisualizationService;
import org.phyloviz.pwp.visualization.service.projects.datasets.trees.TreeVisualizationService;
import org.phyloviz.pwp.visualization.service.projects.files.isolateData.IsolateDataVisualizationService;
import org.phyloviz.pwp.visualization.service.projects.files.typingData.TypingDataVisualizationService;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for the Visualization Microservice.
 */
@RestController
@RequestMapping("/visualization")
@RequiredArgsConstructor
public class VisualizationController {

    private final TreeVisualizationService treeVisualizationService;
    private final TreeViewVisualizationService treeViewVisualizationService;
    private final DistanceMatrixVisualizationService distanceMatrixVisualizationService;
    private final TypingDataVisualizationService typingDataVisualizationService;
    private final IsolateDataVisualizationService isolateDataVisualizationService;

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
            @PathVariable String treeId,
            User user
    ) {
        return treeVisualizationService.getTree(new GetTreeInputDTO(projectId, datasetId, treeId, user.toDTO()));
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
            @PathVariable String treeViewId,
            User user
    ) {
        GetTreeViewOutputDTO getTreeViewOutputDTO = treeViewVisualizationService.getTreeView(
                new GetTreeViewInputDTO(projectId, datasetId, treeViewId, user.toDTO())
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
            @PathVariable String distanceMatrixId,
            User user
    ) {
        return distanceMatrixVisualizationService.getDistanceMatrix(
                new GetDistanceMatrixInputDTO(projectId, datasetId, distanceMatrixId, user.toDTO())
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
            @PathVariable String typingDataId,
            User user
    ) {
        GetTypingDataSchemaOutputDTO getTypingDataSchemaOutputDTO = typingDataVisualizationService.getTypingDataSchema(
                new GetTypingDataSchemaInputDTO(projectId, typingDataId, user.toDTO())
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
            @RequestParam int offset,
            User user
    ) {
        GetTypingDataProfilesOutputDTO getTypingDataProfilesOutputDTO = typingDataVisualizationService.getTypingDataProfiles(
                new GetTypingDataProfilesInputDTO(projectId, typingDataId, limit, offset, user.toDTO())
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
            @PathVariable String isolateDataId,
            User user
    ) {
        GetIsolateDataSchemaOutputDTO getIsolateDataSchemaOutputDTO = isolateDataVisualizationService.getIsolateDataSchema(
                new GetIsolateDataSchemaInputDTO(projectId, isolateDataId, user.toDTO())
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
            @RequestParam int offset,
            User user
    ) {
        GetIsolateDataRowsOutputDTO getIsolateDataRowsOutputDTO = isolateDataVisualizationService.getIsolateDataRows(
                new GetIsolateDataRowsInputDTO(projectId, isolateDataId, limit, offset, user.toDTO())
        );

        return new GetIsolateDataRowsOutputModel(getIsolateDataRowsOutputDTO);
    }
}
