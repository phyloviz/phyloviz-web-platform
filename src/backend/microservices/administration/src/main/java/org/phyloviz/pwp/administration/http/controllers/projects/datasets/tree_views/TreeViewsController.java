package org.phyloviz.pwp.administration.http.controllers.projects.datasets.tree_views;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.http.models.distance_matrices.update_distance_matrix.UpdateDistanceMatrixInputModel;
import org.phyloviz.pwp.administration.http.models.distance_matrices.update_distance_matrix.UpdateDistanceMatrixOutputModel;
import org.phyloviz.pwp.administration.http.models.tree_views.delete_tree_view.DeleteTreeViewOutputModel;
import org.phyloviz.pwp.administration.http.models.tree_views.update_tree_view.UpdateTreeViewInputModel;
import org.phyloviz.pwp.administration.http.models.tree_views.update_tree_view.UpdateTreeViewOutputModel;
import org.phyloviz.pwp.administration.service.dtos.distance_matrix.UpdateDistanceMatrixOutput;
import org.phyloviz.pwp.administration.service.dtos.tree_view.UpdateTreeViewOutput;
import org.phyloviz.pwp.administration.service.project.dataset.tree_view.TreeViewService;
import org.phyloviz.pwp.shared.domain.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that handles requests related to tree views.
 */
@RestController
@RequiredArgsConstructor
public class TreeViewsController {

    private final TreeViewService treeViewService;

    /**
     * Deletes a tree view.
     *
     * @param projectId  the id of the project that contains the tree view
     * @param datasetId  the id of the dataset that contains the tree view
     * @param treeViewId the id of the tree view to be deleted
     * @param user       the user that is deleting the tree view
     * @return information about the deleted tree view
     */
    @DeleteMapping("/projects/{projectId}/datasets/{datasetId}/tree-views/{treeViewId}")
    public DeleteTreeViewOutputModel deleteTreeView(
            @PathVariable String projectId,
            @PathVariable String datasetId,
            @PathVariable String treeViewId,
            User user
    ) {
        treeViewService.deleteTreeView(projectId, datasetId, treeViewId, user.getId());

        return new DeleteTreeViewOutputModel(projectId, datasetId, treeViewId);
    }

    /**
     * Updates a tree view.
     *
     * @param projectId    the id of the project that contains the tree view
     * @param datasetId    the id of the dataset that contains the tree view
     * @param treeViewId the id of the tree view to be updated
     * @param user         the user that is updating the tree view
     * @return information about the updated tree view
     */
    @PatchMapping("/projects/{projectId}/datasets/{datasetId}/tree-views/{treeViewId}")
    public UpdateTreeViewOutputModel updateTreeView(
            @PathVariable String projectId,
            @PathVariable String datasetId,
            @PathVariable String treeViewId,
            @RequestBody UpdateTreeViewInputModel updateTreeViewInputModel,
            User user
    ) {
        UpdateTreeViewOutput updateTreeViewOutput = treeViewService.updateTreeView(
                updateTreeViewInputModel.getName(), projectId, datasetId, treeViewId, user.getId()
        );

        return new UpdateTreeViewOutputModel(updateTreeViewOutput);
    }
}
