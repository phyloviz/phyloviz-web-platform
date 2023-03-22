package org.phyloviz.pwp.administration.http.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.phyloviz.pwp.administration.http.controllers.models.createProject.CreateProjectInputModel;
import org.phyloviz.pwp.administration.http.controllers.models.createProject.CreateProjectOutputModel;
import org.phyloviz.pwp.administration.http.controllers.models.deleteDistanceMatrix.DeleteDistanceMatrixOutputModel;
import org.phyloviz.pwp.administration.http.controllers.models.deleteInferenceTree.DeleteInferenceTreeOutputModel;
import org.phyloviz.pwp.administration.http.controllers.models.deleteProject.DeleteProjectOutputModel;
import org.phyloviz.pwp.administration.http.controllers.models.deleteTreeView.DeleteTreeViewOutputModel;
import org.phyloviz.pwp.administration.http.controllers.models.deleteTypingDataset.DeleteTypingDatasetOutputModel;
import org.phyloviz.pwp.administration.http.controllers.models.getProject.GetProjectOutputModel;
import org.phyloviz.pwp.administration.http.controllers.models.getProjects.GetProjectsOutputModel;
import org.phyloviz.pwp.administration.http.controllers.models.uploadTypingDataset.UploadTypingDatasetOutputModel;
import org.phyloviz.pwp.administration.service.AdministrationService;
import org.phyloviz.pwp.administration.service.dtos.ProjectDTO;
import org.phyloviz.pwp.administration.service.dtos.createProject.CreateProjectOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.deleteDistanceMatrix.DeleteDistanceMatrixInputDTO;
import org.phyloviz.pwp.administration.service.dtos.deleteDistanceMatrix.DeleteDistanceMatrixOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.deleteInferenceTree.DeleteInferenceTreeInputDTO;
import org.phyloviz.pwp.administration.service.dtos.deleteInferenceTree.DeleteInferenceTreeOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.deleteProject.DeleteProjectInputDTO;
import org.phyloviz.pwp.administration.service.dtos.deleteProject.DeleteProjectOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.deleteTreeView.DeleteTreeViewInputDTO;
import org.phyloviz.pwp.administration.service.dtos.deleteTreeView.DeleteTreeViewOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.deleteTypingDataset.DeleteTypingDatasetInputDTO;
import org.phyloviz.pwp.administration.service.dtos.deleteTypingDataset.DeleteTypingDatasetOutputDTO;
import org.phyloviz.pwp.administration.service.dtos.uploadTypingDataset.UploadTypingDatasetOutputDTO;
import org.phyloviz.pwp.shared.domain.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller for the Administration Microservice.
 */
@RestController
@RequiredArgsConstructor
public class AdministrationController {

    private final AdministrationService administrationService;

    /**
     * Creates a project.
     *
     * @param createProjectInputModel the project to be created following the CreateProjectModel format
     * @return a message indicating that the project was successfully created
     */
    @PostMapping("/projects")
    public CreateProjectOutputModel createProject(
            @RequestBody CreateProjectInputModel createProjectInputModel,
            User user
    ) {
        CreateProjectOutputDTO createProjectOutputDTO = administrationService.createProject(
                createProjectInputModel.toDTO(user)
        );

        return new CreateProjectOutputModel(createProjectOutputDTO);
    }


    /**
     * Deletes a project.
     *
     * @param id   the id of the project to be deleted
     * @param user the user that is deleting the project
     */
    @DeleteMapping("/projects/{id}")
    public DeleteProjectOutputModel deleteProject(
            @PathVariable String id,
            User user
    ) {
        DeleteProjectOutputDTO deleteProjectOutputDTO = administrationService.deleteProject(
                new DeleteProjectInputDTO(id, user.toDTO())
        );

        return new DeleteProjectOutputModel(deleteProjectOutputDTO);
    }

    /**
     * Uploads a typing dataset.
     *
     * @param projectId the name of the project to which the typing dataset will be uploaded
     * @param file      the file to be uploaded
     * @return a message indicating that the data was successfully uploaded
     */
    @PostMapping(path = "/typing-datasets", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public UploadTypingDatasetOutputModel uploadTypingDataset(
            @RequestParam String projectId,
            @RequestPart MultipartFile file,
            User user
    ) {
        UploadTypingDatasetOutputDTO uploadTypingDatasetOutputDTO = administrationService.uploadTypingDataset(projectId, file, user.toDTO());

        return new UploadTypingDatasetOutputModel(uploadTypingDatasetOutputDTO);
    }

    /**
     * Deletes a typing dataset.
     *
     * @param id   the id of the typing dataset to be deleted
     * @param user the user that is deleting the typing dataset
     */
    @DeleteMapping("/typing-datasets/{id}")
    public DeleteTypingDatasetOutputModel deleteTypingDataset(
            @PathVariable String id,
            User user
    ) {
        DeleteTypingDatasetOutputDTO deleteTypingDatasetOutputDTO = administrationService.deleteTypingDataset(
                new DeleteTypingDatasetInputDTO(id, user.toDTO())
        );

        return new DeleteTypingDatasetOutputModel(deleteTypingDatasetOutputDTO);
    }

    /**
     * Deletes a distance matrix.
     *
     * @param id   the id of the distance matrix to be deleted
     * @param user the user that is deleting the distance matrix
     */
    @DeleteMapping("/distance-matrices/{id}")
    public DeleteDistanceMatrixOutputModel deleteDistanceMatrix(
            @PathVariable String id,
            User user
    ) {
        DeleteDistanceMatrixOutputDTO deleteDistanceMatrixOutputDTO = administrationService.deleteDistanceMatrix(
                new DeleteDistanceMatrixInputDTO(id, user.toDTO())
        );

        return new DeleteDistanceMatrixOutputModel(deleteDistanceMatrixOutputDTO);
    }

    /**
     * Deletes an inference tree.
     *
     * @param id   the id of the inference tree to be deleted
     * @param user the user that is deleting the inference tree
     */
    @DeleteMapping("/inference-trees/{id}")
    public DeleteInferenceTreeOutputModel deleteInferenceTree(
            @PathVariable String id,
            User user
    ) {
        DeleteInferenceTreeOutputDTO deleteInferenceTreeOutputDTO = administrationService.deleteInferenceTree(
                new DeleteInferenceTreeInputDTO(id, user.toDTO())
        );

        return new DeleteInferenceTreeOutputModel(deleteInferenceTreeOutputDTO);
    }

    /**
     * Deletes a tree view.
     *
     * @param id   the id of the tree view to be deleted
     * @param user the user that is deleting the tree view
     */
    @DeleteMapping("/tree-views/{id}")
    public DeleteTreeViewOutputModel deleteTreeView(
            @PathVariable String id,
            User user
    ) {
        DeleteTreeViewOutputDTO deleteTreeViewOutputDTO = administrationService.deleteTreeView(
                new DeleteTreeViewInputDTO(id, user.toDTO())
        );

        return new DeleteTreeViewOutputModel(deleteTreeViewOutputDTO);
    }

    /**
     * Gets a project.
     *
     * @param projectId the id of the project to be retrieved
     * @param user      the user that is retrieving the project
     * @return the project
     */
    @GetMapping("/projects/{projectId}")
    public GetProjectOutputModel getProject(
            @PathVariable String projectId,
            User user
    ) {
        ProjectDTO projectDTO = administrationService.getProject(projectId, user.toDTO());

        return new GetProjectOutputModel(projectDTO);
    }


    /**
     * Gets all projects belonging to a certain user.
     *
     * @param user the user that is retrieving the projects
     * @return the projects
     */
    @GetMapping("/projects")
    public GetProjectsOutputModel getProjects(
            User user
    ) {
        List<ProjectDTO> projectDTOS = administrationService.getProjects(user.toDTO());

        return new GetProjectsOutputModel(projectDTOS);
    }
}
