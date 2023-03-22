package org.phyloviz.pwp.administration.service;

import org.phyloviz.pwp.administration.service.dtos.ProjectDTO;
import org.phyloviz.pwp.administration.service.dtos.createProject.CreateProjectInputDTO;
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
import org.phyloviz.pwp.shared.service.dtos.UserDTO;
import org.phyloviz.pwp.administration.service.exceptions.ProjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service for the Uploader Microservice.
 */
@Service
public interface AdministrationService {

    /**
     * Creates a project.
     *
     * @param createProjectInputDTO the input data for the project creation
     * @return the output data for the project creation
     */
    CreateProjectOutputDTO createProject(CreateProjectInputDTO createProjectInputDTO);

    /**
     * Deletes a project.
     *
     * @param deleteProjectInputDTO the input data for the project deletion
     * @return the output data for the project deletion
     */
    DeleteProjectOutputDTO deleteProject(DeleteProjectInputDTO deleteProjectInputDTO) throws ProjectNotFoundException;

    /**
     * Stores the uploaded file.
     *
     * @param projectId     id of the project
     * @param multipartFile file to be stored
     * @param userDTO       user who is uploading the file
     * @return the output data for the profile upload
     */
    UploadTypingDatasetOutputDTO uploadTypingDataset(String projectId, MultipartFile multipartFile, UserDTO userDTO);

    /**
     * Deletes a typing dataset.
     *
     * @param deleteTypingDatasetInputDTO the input data for the typing dataset deletion
     * @return the output data for the typing dataset deletion
     */
    DeleteTypingDatasetOutputDTO deleteTypingDataset(DeleteTypingDatasetInputDTO deleteTypingDatasetInputDTO);

    /**
     * Deletes a distance matrix.
     *
     * @param deleteDistanceMatrixInputDTO the input data for the distance matrix deletion
     * @return the output data for the distance matrix deletion
     */
    DeleteDistanceMatrixOutputDTO deleteDistanceMatrix(DeleteDistanceMatrixInputDTO deleteDistanceMatrixInputDTO);

    /**
     * Deletes an inference tree.
     *
     * @param deleteInferenceTreeInputDTO the input data for the inference tree deletion
     * @return the output data for the inference tree deletion
     */
    DeleteInferenceTreeOutputDTO deleteInferenceTree(DeleteInferenceTreeInputDTO deleteInferenceTreeInputDTO);

    /**
     * Deletes a tree view.
     *
     * @param deleteTreeViewInputDTO the input data for the tree view deletion
     * @return the output data for the tree view deletion
     */
    DeleteTreeViewOutputDTO deleteTreeView(DeleteTreeViewInputDTO deleteTreeViewInputDTO);

    /**
     * Gets a project.
     *
     * @param projectId id of the project
     * @param toDTO     user that wants to get the project
     * @return the project
     */
    ProjectDTO getProject(String projectId, UserDTO toDTO);


    /**
     * Gets all projects of a user.
     *
     * @param userDTO user that wants to get the projects
     * @return the projects
     */
    List<ProjectDTO> getProjects(UserDTO userDTO);
}
