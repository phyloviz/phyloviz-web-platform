package org.phyloviz.pwp.compute.http.controllers;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.compute.http.controllers.models.createWorkflow.CreateWorkflowInputModel;
import org.phyloviz.pwp.compute.http.controllers.models.createWorkflow.CreateWorkflowOutputModel;
import org.phyloviz.pwp.compute.http.controllers.models.getWorkflowStatus.GetWorkflowStatusOutputModel;
import org.phyloviz.pwp.compute.http.controllers.models.getWorkflows.GetWorkflowsOutputModel;
import org.phyloviz.pwp.compute.service.ComputeService;
import org.phyloviz.pwp.compute.service.dtos.createWorkflow.CreateWorkflowOutput;
import org.phyloviz.pwp.compute.service.dtos.getWorkflow.GetWorkflowStatusOutput;
import org.phyloviz.pwp.shared.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for the Compute Microservice.
 */
@RestController
@RequiredArgsConstructor
public class ComputeController {

    private final ComputeService computeService;

    /**
     * Create a new workflow.
     *
     * @param inputModel input model for the workflow
     * @param projectId  the project id of the project to which the workflow belongs
     * @param user       the user who is creating the workflow
     * @return information about the created workflow
     */
    @PostMapping("/projects/{projectId}/workflows")
    public CreateWorkflowOutputModel createWorkflow(
            @PathVariable String projectId,
            @RequestBody CreateWorkflowInputModel inputModel,
            User user
    ) {
        CreateWorkflowOutput createWorkflowOutput = computeService.createWorkflow(
                projectId, inputModel.getType(), inputModel.getProperties(), user.getId()
        );

        return new CreateWorkflowOutputModel(createWorkflowOutput);
    }

    /**
     * Gets the status of a workflow.
     *
     * @param projectId  the project id of the project to which the workflow belongs
     * @param workflowId the id of the workflow
     * @param user       the user who is requesting the workflow status
     * @return information about the workflow
     */
    @GetMapping("/projects/{projectId}/workflows/{workflowId}")
    public GetWorkflowStatusOutputModel getWorkflowStatus(
            @PathVariable String projectId,
            @PathVariable String workflowId,
            User user
    ) {
        GetWorkflowStatusOutput getWorkflowStatusOutput = computeService.getWorkflowStatus(
                projectId, workflowId, user.getId()
        );

        return new GetWorkflowStatusOutputModel(getWorkflowStatusOutput);
    }

    /**
     * Gets the workflows of a project.
     *
     * @param projectId the project id of the project to which the workflow belongs
     * @param user      the user who is requesting the workflows
     * @return information about the workflows
     */
    @GetMapping("/projects/{projectId}/workflows")
    public GetWorkflowsOutputModel getWorkflows(
            @PathVariable String projectId,
            User user
    ) {
        List<GetWorkflowStatusOutput> getWorkflowStatusOutputList = computeService.getWorkflows(
                projectId, user.getId()
        );

        return new GetWorkflowsOutputModel(getWorkflowStatusOutputList);
    }
}
