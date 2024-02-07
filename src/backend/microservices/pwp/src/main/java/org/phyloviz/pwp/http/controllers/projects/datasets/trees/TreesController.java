package org.phyloviz.pwp.http.controllers.projects.datasets.trees;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.http.models.trees.delete_tree.DeleteTreeOutputModel;
import org.phyloviz.pwp.http.models.trees.update_tree_view.UpdateTreeInputModel;
import org.phyloviz.pwp.http.models.trees.update_tree_view.UpdateTreeOutputModel;
import org.phyloviz.pwp.service.dtos.tree.UpdateTreeOutput;
import org.phyloviz.pwp.service.project.dataset.tree.TreeService;
import org.phyloviz.pwp.domain.User;
import org.springframework.web.bind.annotation.*;

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
