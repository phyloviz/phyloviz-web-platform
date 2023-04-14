package org.phyloviz.pwp.administration.http.controllers.projects.datasets.trees;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.http.models.trees.deleteTree.DeleteTreeOutputModel;
import org.phyloviz.pwp.administration.service.dtos.trees.deleteTree.DeleteTreeInputDTO;
import org.phyloviz.pwp.administration.service.dtos.trees.deleteTree.DeleteTreeOutputDTO;
import org.phyloviz.pwp.administration.service.projects.datasets.trees.TreesService;
import org.phyloviz.pwp.shared.domain.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that handles requests related to trees.
 */
@RestController
@RequiredArgsConstructor
public class TreesController {

    private final TreesService treesService;

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
        DeleteTreeOutputDTO deleteTreeOutputDTO = treesService.deleteTree(
                new DeleteTreeInputDTO(projectId, datasetId, treeId, user.toDTO())
        );

        return new DeleteTreeOutputModel(deleteTreeOutputDTO);
    }
}
