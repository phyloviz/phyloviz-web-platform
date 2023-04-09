package org.phyloviz.pwp.compute.http.controllers;

import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.compute.http.controllers.models.computeDistanceMatrix.CreateWorkflowInputModel;
import org.phyloviz.pwp.compute.http.controllers.models.computeDistanceMatrix.CreateWorkflowOutputModel;
import org.phyloviz.pwp.compute.http.controllers.models.getWorkflow.GetWorkflowOutputModel;
import org.phyloviz.pwp.compute.service.ComputeService;
import org.phyloviz.pwp.compute.service.dtos.computeDistanceMatrix.CreateWorkflowOutputDTO;
import org.phyloviz.pwp.compute.service.dtos.getWorkflow.GetWorkflowOutputDTO;
import org.phyloviz.pwp.shared.domain.User;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for the Compute Microservice.
 */
@RestController
@RequiredArgsConstructor
public class ComputeController {

    private final ComputeService computeService;


    // TODO: 11/03/2023 To be implemented.
    @PostMapping("/projects/{projectId}/workflows")
    public CreateWorkflowOutputModel createWorkflow(
            @RequestBody CreateWorkflowInputModel inputModel,
            @PathVariable String projectId,
            User user
    ) {
        CreateWorkflowOutputDTO createWorkflowOutputDTO = computeService.createWorkflow(projectId, inputModel.getType(), inputModel.getProperties(), user.toDTO());

        return new CreateWorkflowOutputModel(createWorkflowOutputDTO);
    }

    @GetMapping("/projects/{projectId}/workflows/{workflowId}")
    public GetWorkflowOutputModel getWorkflow(
            @PathVariable String projectId,
            @PathVariable String workflowId,
            User user
    ) {
        GetWorkflowOutputDTO getWorkflowOutputDTO = computeService.getWorkflow(projectId, workflowId, user.toDTO());

        return new GetWorkflowOutputModel(getWorkflowOutputDTO);
    }
}
