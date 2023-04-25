package org.phyloviz.pwp.administration.http.controllers.projects.datasets.trees;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.http.models.trees.delete_tree.DeleteTreeOutputModel;
import org.phyloviz.pwp.administration.http.models.trees.update_tree_view.UpdateTreeInputModel;
import org.phyloviz.pwp.administration.http.models.trees.update_tree_view.UpdateTreeOutputModel;
import org.phyloviz.pwp.administration.service.dtos.tree.UpdateTreeOutput;
import org.phyloviz.pwp.administration.service.project.dataset.tree.TreeService;
import org.phyloviz.pwp.shared.domain.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that handles requests related to trees.
 */
@RestController
@RequiredArgsConstructor
public class TreesController {

    private final TreeService treeService;

    /**
     * Deletes a tree.
     *
     * @param projectId the id of the project that contains the tree
     * @param datasetId the id of the dataset that contains the tree
     * @param treeId    the id of the tree to be deleted
     * @param user      the user that is deleting the tree
     * @return information about the deleted tree
     */
    @DeleteMapping("/projects/{projectId}/datasets/{datasetId}/trees/{treeId}")
    public DeleteTreeOutputModel deleteTree(
            @PathVariable String projectId,
            @PathVariable String datasetId,
            @PathVariable String treeId,
            User user
    ) {
        treeService.deleteTree(projectId, datasetId, treeId, user.getId());

        return new DeleteTreeOutputModel(projectId, datasetId, treeId);
    }

    /**
     * Updates a tree.
     *
     * @param projectId the id of the project that contains the tree
     * @param datasetId the id of the dataset that contains the tree
     * @param treeId    the id of the tree to be updated
     * @param user      the user that is updating the tree
     * @return information about the updated tree
     */
    @PatchMapping("/projects/{projectId}/datasets/{datasetId}/trees/{treeId}")
    public UpdateTreeOutputModel updateTree(
            @PathVariable String projectId,
            @PathVariable String datasetId,
            @PathVariable String treeId,
            @RequestBody UpdateTreeInputModel updateTreeInputModel,
            User user
    ) {
        UpdateTreeOutput updateTreeOutput = treeService.updateTree(
                updateTreeInputModel.getName(), projectId, datasetId, treeId, user.getId()
        );

        return new UpdateTreeOutputModel(updateTreeOutput);
    }
}
