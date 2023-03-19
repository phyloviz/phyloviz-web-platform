package org.phyloviz.pwp.uploader.service;

import org.phyloviz.pwp.shared.service.dtos.UserDTO;
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
     * @param userDTO       user who is uploading the file
     * @return the output data for the profile upload
     */
    UploadProfileOutputDTO uploadProfile(String projectId, MultipartFile multipartFile, UserDTO userDTO);
}
