package org.phyloviz.pwp.administration.http.controllers.projects.datasets.treeViews;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.http.models.treeViews.deleteTreeView.DeleteTreeViewOutputModel;
import org.phyloviz.pwp.administration.service.dtos.treeViews.deleteTreeView.DeleteTreeViewInputDTO;
import org.phyloviz.pwp.administration.service.dtos.treeViews.deleteTreeView.DeleteTreeViewOutputDTO;
import org.phyloviz.pwp.administration.service.projects.datasets.treeViews.TreeViewsService;
import org.phyloviz.pwp.shared.domain.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that handles requests related to tree views.
 */
@RestController
@RequiredArgsConstructor
public class TreeViewsController {

    private final TreeViewsService treeViewsService;

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
        DeleteTreeViewOutputDTO deleteTreeViewOutputDTO = treeViewsService.deleteTreeView(
                new DeleteTreeViewInputDTO(projectId, datasetId, treeViewId, user.toDTO())
        );

        return new DeleteTreeViewOutputModel(deleteTreeViewOutputDTO);
    }
}
