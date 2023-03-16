package org.phyloviz.pwp.uploader.service;

import org.phyloviz.pwp.uploader.service.dtos.createProject.CreateProjectInputDTO;
import org.phyloviz.pwp.uploader.service.dtos.createProject.CreateProjectOutputDTO;
import org.phyloviz.pwp.uploader.service.dtos.uploadeProfile.UploadProfileOutputDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service for the Uploader Microservice.
 */
@Service
public interface UploadService {

    /**
     * Stores the uploaded file.
     *
     * @param projectId     id of the project
     * @param multipartFile file to be stored
     * @return the output data for the profile upload
     */
    UploadProfileOutputDTO storeProfile(String projectId, MultipartFile multipartFile);

    /**
     * Creates a project.
     *
     * @param createProjectInputDTO the input data for the project creation
     * @return the output data for the project creation
     */
    CreateProjectOutputDTO createProject(CreateProjectInputDTO createProjectInputDTO);
}
